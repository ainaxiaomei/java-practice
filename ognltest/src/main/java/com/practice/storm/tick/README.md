##storm的tick机制
* torm的tick机制需要重写getComponentConfiguration方法
       
     public Map<String, Object> getComponentConfiguration() {
		Config conf = new  Config();
		conf.put(Config.TOPOLOGY_TICK_TUPLE_FREQ_SECS, 5);
		return conf;
	}
    
