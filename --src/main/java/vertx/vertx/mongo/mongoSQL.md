#moinngo vertx查询

##大于小于
```java
new JsonObject() .put("timeStamp",  new JsonObject().
                                        put("$gte",1546272000000L)
                                        .put("$lte",1548864000000L));
```

## update list
```java
//list index=6值加10.0
new JsonObject() .put("$inc",new JsonObject()
                 .put("dataList.6",10.0))
```

##分页+排序
```java
// 
new FindOptions().setLimit(pageSize)
                 .setSkip((page-1)*pageSize)
                 .setSort(new JsonObject().put("timeStamp",1))
```

## 多update
```java
new JsonObject().put("$set",new JsonObject().put("valueW",value))
                .put("$inc",new JsonObject().put("listW."+hour,use));
```