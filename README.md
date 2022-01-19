# Springboot-Redis


需要注意的就是在我们定义entity实体类的时候，因为我们是需要将java对象存储在redis中，```redis又是在内存中进行存储```

所以我们一定要让实体类```实现序列化的接口```。


具体在```controller```控制器中去控制相关操作
