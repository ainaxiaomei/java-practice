## storm的并行性
* storm配置文件的执行顺序

	  defaults.yaml < storm.yaml < topology-specific configuration < internal component-specific configuration < external component-specific configuration
* worker,executor和task
    *  worker是一个进程,executort是一个worker中的线程，task是具体执行的任务
    *  一个executor可以运行多个task
    *  worker是提交topology时创建的

* parallelism_hint和setNumTasks
    *  parallelism_hint设置executor的数量， setNumTasks设置执行任务的数量executor <= tasks
    *  如果设置了parallelism_hint为4同时设置了setNumTasks为1只会有一个task
    
    

