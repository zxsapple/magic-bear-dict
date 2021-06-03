package com.magic.bear.dict.client.config;

import com.magic.bear.dict.client.annotation.DictConvert;
import com.magic.bear.dict.client.annotation.EnableDictTransfer;
import com.magic.bear.dict.client.annotation.OpenDict;
import com.magic.bear.dict.client.dto.DictTransferDto;
import com.magic.bear.dict.client.dto.NewPropertyInfoDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.springframework.context.annotation.ImportAware;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.*;

/**
 * @author zxs
 * @date 2021/6/3 9:38
 * @desc
 */
@Slf4j
public class InitDictTransferConfig implements ImportAware {
    // 扫描的包名
    public static final Map<Class<?>, DictTransferDto> CLASS_CACHE = new HashMap<>();

    //新字段后缀
    private String targetFieldSuffix="Str";
    @Override
    public void setImportMetadata(AnnotationMetadata annotationMetadata) {
        Map<String, Object> attributesMap = annotationMetadata.getAnnotationAttributes(EnableDictTransfer.class.getName());
        AnnotationAttributes attrs = AnnotationAttributes.fromMap(attributesMap);
        String[] basePackages = attrs.getStringArray("basePackages");
        if (StringUtils.isEmpty(basePackages[0])) {
            String appClassName = annotationMetadata.getClassName();
            int lastDotIndex = appClassName.lastIndexOf(".");
            basePackages = new String[]{appClassName.substring(0, lastDotIndex)};
        }
        
        scanUsedClass(basePackages);
    }

    public void scanUsedClass(String[] basePackages) {
        ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
        final String RESOURCE_PATTERN = "/**/*.class";

        try {
            for (String basePackage : basePackages) {
                String pattern = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX + ClassUtils.convertClassNameToResourcePath(basePackage) + RESOURCE_PATTERN;
                Resource[] resources = resourcePatternResolver.getResources(pattern);
                MetadataReaderFactory readerFactory = new CachingMetadataReaderFactory(resourcePatternResolver);
                for (Resource resource : resources) {
                    if (resource.isReadable()) {
                        MetadataReader reader = readerFactory.getMetadataReader(resource);
                        //扫描到的class
                        String className = reader.getClassMetadata().getClassName();
                        Class<?> clz = Class.forName(className);
                        //判断是否有指定注解
                        OpenDict annotation = clz.getAnnotation(OpenDict.class);
                        if (annotation != null) {
                            DictTransferDto dictTransferDto = getDictTransferDto(clz);
                            CLASS_CACHE.put(clz, dictTransferDto);
                        }
                    }
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            log.error("读取class失败", e);
        }
    }

    /**
     * 根据class信息转换成新旧对应关系
     */
    private DictTransferDto getDictTransferDto(Class<?> clz) {
        //原有字段 + 新增字段
        Map<String, Class<?>> propertyMap = new HashMap<>();
        //原有字段与新字段关系
        Map<String, NewPropertyInfoDto> origin2TargetMap = new HashMap<>();
        //待转新字段
        Set<String> targetSet = new HashSet<>();
        //原来所有field
        List<Field> allFieldsList = FieldUtils.getAllFieldsList(clz);

        for (Field field : allFieldsList) {
            propertyMap.put(field.getName(), field.getType());
            DictConvert annotation = field.getAnnotation(DictConvert.class);
            if (annotation == null) {
                continue;
            }
            String type = annotation.type();

            String sourceKey = field.getName();

            String targetKey = annotation.targetKey();

            //使用默认值
            if (StringUtils.isEmpty(type)) {
                type = sourceKey;
            }
            if (StringUtils.isEmpty(targetKey)) {
                targetKey = sourceKey + targetFieldSuffix;
            }

            NewPropertyInfoDto newPropertyInfoDto = new NewPropertyInfoDto();

            newPropertyInfoDto.setType(type);
            newPropertyInfoDto.setTargetKey(targetKey);

            origin2TargetMap.put(sourceKey, newPropertyInfoDto);
            targetSet.add(targetKey);
            propertyMap.put(targetKey, String.class);
        }
        DictTransferDto dictTransferDto = new DictTransferDto();
        dictTransferDto.setPropertyMap(propertyMap);
        dictTransferDto.setOrigin2TargetMap(origin2TargetMap);
        dictTransferDto.setTargetSet(targetSet);
        return dictTransferDto;
    }
}