package com.fasterxml.jackson.databind.introspect;

import com.fasterxml.jackson.annotation.JsonFormat.Value;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.AnnotationIntrospector.ReferenceProperty;
import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.PropertyName;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder.Value;
import com.fasterxml.jackson.databind.cfg.HandlerInstantiator;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.type.TypeBindings;
import com.fasterxml.jackson.databind.util.Annotations;
import com.fasterxml.jackson.databind.util.ClassUtil;
import com.fasterxml.jackson.databind.util.Converter;
import com.fasterxml.jackson.databind.util.Converter.None;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class BasicBeanDescription
  extends BeanDescription
{
  protected final AnnotationIntrospector _annotationIntrospector;
  protected AnnotatedMember _anyGetter;
  protected AnnotatedMethod _anySetterMethod;
  protected TypeBindings _bindings;
  protected final AnnotatedClass _classInfo;
  protected final MapperConfig<?> _config;
  protected Set<String> _ignoredPropertyNames;
  protected Map<Object, AnnotatedMember> _injectables;
  protected AnnotatedMethod _jsonValueMethod;
  protected ObjectIdInfo _objectIdInfo;
  protected final List<BeanPropertyDefinition> _properties;
  
  protected BasicBeanDescription(MapperConfig<?> paramMapperConfig, JavaType paramJavaType, AnnotatedClass paramAnnotatedClass, List<BeanPropertyDefinition> paramList)
  {
    super(paramJavaType);
    this._config = paramMapperConfig;
    if (paramMapperConfig == null) {}
    for (paramMapperConfig = null;; paramMapperConfig = paramMapperConfig.getAnnotationIntrospector())
    {
      this._annotationIntrospector = paramMapperConfig;
      this._classInfo = paramAnnotatedClass;
      this._properties = paramList;
      return;
    }
  }
  
  protected BasicBeanDescription(POJOPropertiesCollector paramPOJOPropertiesCollector)
  {
    this(paramPOJOPropertiesCollector.getConfig(), paramPOJOPropertiesCollector.getType(), paramPOJOPropertiesCollector.getClassDef(), paramPOJOPropertiesCollector.getProperties());
    this._objectIdInfo = paramPOJOPropertiesCollector.getObjectIdInfo();
  }
  
  public static BasicBeanDescription forDeserialization(POJOPropertiesCollector paramPOJOPropertiesCollector)
  {
    BasicBeanDescription localBasicBeanDescription = new BasicBeanDescription(paramPOJOPropertiesCollector);
    localBasicBeanDescription._anySetterMethod = paramPOJOPropertiesCollector.getAnySetterMethod();
    localBasicBeanDescription._ignoredPropertyNames = paramPOJOPropertiesCollector.getIgnoredPropertyNames();
    localBasicBeanDescription._injectables = paramPOJOPropertiesCollector.getInjectables();
    localBasicBeanDescription._jsonValueMethod = paramPOJOPropertiesCollector.getJsonValueMethod();
    return localBasicBeanDescription;
  }
  
  public static BasicBeanDescription forOtherUse(MapperConfig<?> paramMapperConfig, JavaType paramJavaType, AnnotatedClass paramAnnotatedClass)
  {
    return new BasicBeanDescription(paramMapperConfig, paramJavaType, paramAnnotatedClass, Collections.emptyList());
  }
  
  public static BasicBeanDescription forSerialization(POJOPropertiesCollector paramPOJOPropertiesCollector)
  {
    BasicBeanDescription localBasicBeanDescription = new BasicBeanDescription(paramPOJOPropertiesCollector);
    localBasicBeanDescription._jsonValueMethod = paramPOJOPropertiesCollector.getJsonValueMethod();
    localBasicBeanDescription._anyGetter = paramPOJOPropertiesCollector.getAnyGetter();
    return localBasicBeanDescription;
  }
  
  public Converter<Object, Object> _createConverter(Object paramObject)
  {
    Object localObject = null;
    if (paramObject == null) {
      return null;
    }
    if ((paramObject instanceof Converter)) {
      return (Converter)paramObject;
    }
    if (!(paramObject instanceof Class)) {
      throw new IllegalStateException("AnnotationIntrospector returned Converter definition of type " + paramObject.getClass().getName() + "; expected type Converter or Class<Converter> instead");
    }
    Class localClass = (Class)paramObject;
    if ((localClass == Converter.None.class) || (ClassUtil.isBogusClass(localClass))) {
      return null;
    }
    if (!Converter.class.isAssignableFrom(localClass)) {
      throw new IllegalStateException("AnnotationIntrospector returned Class " + localClass.getName() + "; expected Class<Converter>");
    }
    paramObject = this._config.getHandlerInstantiator();
    if (paramObject == null) {}
    for (paramObject = localObject;; paramObject = ((HandlerInstantiator)paramObject).converterInstance(this._config, this._classInfo, localClass))
    {
      localObject = paramObject;
      if (paramObject == null) {
        localObject = (Converter)ClassUtil.createInstance(localClass, this._config.canOverrideAccessModifiers());
      }
      return (Converter<Object, Object>)localObject;
    }
  }
  
  protected PropertyName _findCreatorPropertyName(AnnotatedParameter paramAnnotatedParameter)
  {
    PropertyName localPropertyName2 = this._annotationIntrospector.findNameForDeserialization(paramAnnotatedParameter);
    PropertyName localPropertyName1;
    if (localPropertyName2 != null)
    {
      localPropertyName1 = localPropertyName2;
      if (!localPropertyName2.isEmpty()) {}
    }
    else
    {
      paramAnnotatedParameter = this._annotationIntrospector.findImplicitPropertyName(paramAnnotatedParameter);
      localPropertyName1 = localPropertyName2;
      if (paramAnnotatedParameter != null)
      {
        localPropertyName1 = localPropertyName2;
        if (!paramAnnotatedParameter.isEmpty()) {
          localPropertyName1 = new PropertyName(paramAnnotatedParameter);
        }
      }
    }
    return localPropertyName1;
  }
  
  public LinkedHashMap<String, AnnotatedField> _findPropertyFields(Collection<String> paramCollection, boolean paramBoolean)
  {
    LinkedHashMap localLinkedHashMap = new LinkedHashMap();
    Iterator localIterator = this._properties.iterator();
    while (localIterator.hasNext())
    {
      Object localObject = (BeanPropertyDefinition)localIterator.next();
      AnnotatedField localAnnotatedField = ((BeanPropertyDefinition)localObject).getField();
      if (localAnnotatedField != null)
      {
        localObject = ((BeanPropertyDefinition)localObject).getName();
        if ((paramCollection == null) || (!paramCollection.contains(localObject))) {
          localLinkedHashMap.put(localObject, localAnnotatedField);
        }
      }
    }
    return localLinkedHashMap;
  }
  
  public TypeBindings bindingsForBeanType()
  {
    if (this._bindings == null) {
      this._bindings = new TypeBindings(this._config.getTypeFactory(), this._type);
    }
    return this._bindings;
  }
  
  public AnnotatedMember findAnyGetter()
    throws IllegalArgumentException
  {
    if ((this._anyGetter != null) && (!Map.class.isAssignableFrom(this._anyGetter.getRawType()))) {
      throw new IllegalArgumentException("Invalid 'any-getter' annotation on method " + this._anyGetter.getName() + "(): return type is not instance of java.util.Map");
    }
    return this._anyGetter;
  }
  
  public AnnotatedMethod findAnySetter()
    throws IllegalArgumentException
  {
    if (this._anySetterMethod != null)
    {
      Class localClass = this._anySetterMethod.getRawParameterType(0);
      if ((localClass != String.class) && (localClass != Object.class)) {
        throw new IllegalArgumentException("Invalid 'any-setter' annotation on method " + this._anySetterMethod.getName() + "(): first argument not of type String or Object, but " + localClass.getName());
      }
    }
    return this._anySetterMethod;
  }
  
  public Map<String, AnnotatedMember> findBackReferenceProperties()
  {
    Object localObject1 = null;
    Iterator localIterator = this._properties.iterator();
    while (localIterator.hasNext())
    {
      AnnotatedMember localAnnotatedMember = ((BeanPropertyDefinition)localIterator.next()).getMutator();
      if (localAnnotatedMember != null)
      {
        Object localObject3 = this._annotationIntrospector.findReferenceType(localAnnotatedMember);
        if ((localObject3 != null) && (((AnnotationIntrospector.ReferenceProperty)localObject3).isBackReference()))
        {
          Object localObject2 = localObject1;
          if (localObject1 == null) {
            localObject2 = new HashMap();
          }
          localObject3 = ((AnnotationIntrospector.ReferenceProperty)localObject3).getName();
          localObject1 = localObject2;
          if (((HashMap)localObject2).put(localObject3, localAnnotatedMember) != null) {
            throw new IllegalArgumentException("Multiple back-reference properties with name '" + (String)localObject3 + "'");
          }
        }
      }
    }
    return (Map<String, AnnotatedMember>)localObject1;
  }
  
  public List<PropertyName> findCreatorParameterNames()
  {
    int i = 0;
    while (i < 2)
    {
      if (i == 0) {}
      for (localObject1 = getConstructors();; localObject1 = getFactoryMethods())
      {
        Object localObject2 = ((List)localObject1).iterator();
        AnnotatedWithParams localAnnotatedWithParams;
        int j;
        do
        {
          do
          {
            if (!((Iterator)localObject2).hasNext()) {
              break;
            }
            localAnnotatedWithParams = (AnnotatedWithParams)((Iterator)localObject2).next();
            j = localAnnotatedWithParams.getParameterCount();
          } while (j < 1);
          localObject1 = _findCreatorPropertyName(localAnnotatedWithParams.getParameter(0));
        } while ((localObject1 == null) || (((PropertyName)localObject1).isEmpty()));
        localObject2 = new ArrayList();
        ((List)localObject2).add(localObject1);
        i = 1;
        for (;;)
        {
          localObject1 = localObject2;
          if (i >= j) {
            break;
          }
          ((List)localObject2).add(_findCreatorPropertyName(localAnnotatedWithParams.getParameter(i)));
          i += 1;
        }
      }
      i += 1;
    }
    Object localObject1 = Collections.emptyList();
    return (List<PropertyName>)localObject1;
  }
  
  @Deprecated
  public List<String> findCreatorPropertyNames()
  {
    Object localObject = findCreatorParameterNames();
    if (((List)localObject).isEmpty())
    {
      localObject = Collections.emptyList();
      return (List<String>)localObject;
    }
    ArrayList localArrayList = new ArrayList(((List)localObject).size());
    Iterator localIterator = ((List)localObject).iterator();
    for (;;)
    {
      localObject = localArrayList;
      if (!localIterator.hasNext()) {
        break;
      }
      localArrayList.add(((PropertyName)localIterator.next()).getSimpleName());
    }
  }
  
  public AnnotatedConstructor findDefaultConstructor()
  {
    return this._classInfo.getDefaultConstructor();
  }
  
  public Converter<Object, Object> findDeserializationConverter()
  {
    if (this._annotationIntrospector == null) {
      return null;
    }
    return _createConverter(this._annotationIntrospector.findDeserializationConverter(this._classInfo));
  }
  
  public JsonFormat.Value findExpectedFormat(JsonFormat.Value paramValue)
  {
    if (this._annotationIntrospector != null)
    {
      JsonFormat.Value localValue = this._annotationIntrospector.findFormat(this._classInfo);
      if (localValue != null) {
        return localValue;
      }
    }
    return paramValue;
  }
  
  public Method findFactoryMethod(Class<?>... paramVarArgs)
  {
    Iterator localIterator = this._classInfo.getStaticMethods().iterator();
    while (localIterator.hasNext())
    {
      AnnotatedMethod localAnnotatedMethod = (AnnotatedMethod)localIterator.next();
      if (isFactoryMethod(localAnnotatedMethod))
      {
        Class localClass = localAnnotatedMethod.getRawParameterType(0);
        int j = paramVarArgs.length;
        int i = 0;
        while (i < j)
        {
          if (localClass.isAssignableFrom(paramVarArgs[i])) {
            return localAnnotatedMethod.getAnnotated();
          }
          i += 1;
        }
      }
    }
    return null;
  }
  
  public Map<Object, AnnotatedMember> findInjectables()
  {
    return this._injectables;
  }
  
  public AnnotatedMethod findJsonValueMethod()
  {
    return this._jsonValueMethod;
  }
  
  public AnnotatedMethod findMethod(String paramString, Class<?>[] paramArrayOfClass)
  {
    return this._classInfo.findMethod(paramString, paramArrayOfClass);
  }
  
  public Class<?> findPOJOBuilder()
  {
    if (this._annotationIntrospector == null) {
      return null;
    }
    return this._annotationIntrospector.findPOJOBuilder(this._classInfo);
  }
  
  public JsonPOJOBuilder.Value findPOJOBuilderConfig()
  {
    if (this._annotationIntrospector == null) {
      return null;
    }
    return this._annotationIntrospector.findPOJOBuilderConfig(this._classInfo);
  }
  
  public List<BeanPropertyDefinition> findProperties()
  {
    return this._properties;
  }
  
  public Converter<Object, Object> findSerializationConverter()
  {
    if (this._annotationIntrospector == null) {
      return null;
    }
    return _createConverter(this._annotationIntrospector.findSerializationConverter(this._classInfo));
  }
  
  public JsonInclude.Include findSerializationInclusion(JsonInclude.Include paramInclude)
  {
    if (this._annotationIntrospector == null) {
      return paramInclude;
    }
    return this._annotationIntrospector.findSerializationInclusion(this._classInfo, paramInclude);
  }
  
  public Constructor<?> findSingleArgConstructor(Class<?>... paramVarArgs)
  {
    Iterator localIterator = this._classInfo.getConstructors().iterator();
    while (localIterator.hasNext())
    {
      AnnotatedConstructor localAnnotatedConstructor = (AnnotatedConstructor)localIterator.next();
      if (localAnnotatedConstructor.getParameterCount() == 1)
      {
        Class localClass = localAnnotatedConstructor.getRawParameterType(0);
        int j = paramVarArgs.length;
        int i = 0;
        while (i < j)
        {
          if (paramVarArgs[i] == localClass) {
            return localAnnotatedConstructor.getAnnotated();
          }
          i += 1;
        }
      }
    }
    return null;
  }
  
  public Annotations getClassAnnotations()
  {
    return this._classInfo.getAnnotations();
  }
  
  public AnnotatedClass getClassInfo()
  {
    return this._classInfo;
  }
  
  public List<AnnotatedConstructor> getConstructors()
  {
    return this._classInfo.getConstructors();
  }
  
  public List<AnnotatedMethod> getFactoryMethods()
  {
    Object localObject = this._classInfo.getStaticMethods();
    if (((List)localObject).isEmpty()) {
      return (List<AnnotatedMethod>)localObject;
    }
    ArrayList localArrayList = new ArrayList();
    localObject = ((List)localObject).iterator();
    while (((Iterator)localObject).hasNext())
    {
      AnnotatedMethod localAnnotatedMethod = (AnnotatedMethod)((Iterator)localObject).next();
      if (isFactoryMethod(localAnnotatedMethod)) {
        localArrayList.add(localAnnotatedMethod);
      }
    }
    return localArrayList;
  }
  
  public Set<String> getIgnoredPropertyNames()
  {
    if (this._ignoredPropertyNames == null) {
      return Collections.emptySet();
    }
    return this._ignoredPropertyNames;
  }
  
  public ObjectIdInfo getObjectIdInfo()
  {
    return this._objectIdInfo;
  }
  
  public boolean hasKnownClassAnnotations()
  {
    return this._classInfo.hasAnnotations();
  }
  
  public Object instantiateBean(boolean paramBoolean)
  {
    Object localObject = this._classInfo.getDefaultConstructor();
    if (localObject == null) {
      return null;
    }
    if (paramBoolean) {
      ((AnnotatedConstructor)localObject).fixAccess();
    }
    try
    {
      localObject = ((AnnotatedConstructor)localObject).getAnnotated().newInstance(new Object[0]);
      return localObject;
    }
    catch (Exception localException)
    {
      Throwable localThrowable;
      while (localException.getCause() != null) {
        localThrowable = localException.getCause();
      }
      if ((localThrowable instanceof Error)) {
        throw ((Error)localThrowable);
      }
      if ((localThrowable instanceof RuntimeException)) {
        throw ((RuntimeException)localThrowable);
      }
      throw new IllegalArgumentException("Failed to instantiate bean of type " + this._classInfo.getAnnotated().getName() + ": (" + localThrowable.getClass().getName() + ") " + localThrowable.getMessage(), localThrowable);
    }
  }
  
  protected boolean isFactoryMethod(AnnotatedMethod paramAnnotatedMethod)
  {
    Object localObject = paramAnnotatedMethod.getRawReturnType();
    if (!getBeanClass().isAssignableFrom((Class)localObject)) {}
    do
    {
      do
      {
        return false;
        if (this._annotationIntrospector.hasCreatorAnnotation(paramAnnotatedMethod)) {
          return true;
        }
        localObject = paramAnnotatedMethod.getName();
        if ("valueOf".equals(localObject)) {
          return true;
        }
      } while ((!"fromString".equals(localObject)) || (1 != paramAnnotatedMethod.getParameterCount()));
      paramAnnotatedMethod = paramAnnotatedMethod.getRawParameterType(0);
    } while ((paramAnnotatedMethod != String.class) && (!CharSequence.class.isAssignableFrom(paramAnnotatedMethod)));
    return true;
  }
  
  public boolean removeProperty(String paramString)
  {
    Iterator localIterator = this._properties.iterator();
    while (localIterator.hasNext()) {
      if (((BeanPropertyDefinition)localIterator.next()).getName().equals(paramString))
      {
        localIterator.remove();
        return true;
      }
    }
    return false;
  }
  
  public JavaType resolveType(Type paramType)
  {
    if (paramType == null) {
      return null;
    }
    return bindingsForBeanType().resolveType(paramType);
  }
}


/* Location:              /home/dev/Downloads/apk/dex2jar-2.0/crumby-dex2jar.jar!/com/fasterxml/jackson/databind/introspect/BasicBeanDescription.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */