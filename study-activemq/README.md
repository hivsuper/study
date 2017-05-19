# study-activemq
### Environment 
ActiveMQ: apache-activemq-5.14.5  
OS: Ubuntu 15.10  
Mysql: 5.6.31-0ubuntu0.15.10.1  
JAVA: 1.7.0_101  
### Q&A  
+	How to integrate ActiveMQ with spring?  
	Refer to: http://elim.iteye.com/blog/1893038  
	http://elim.iteye.com/blog/1900937
+	How to install ActiveMQ?  
	1. Download software from http://activemq.apache.org/download.html.  
	2. Extract the files to /data/activemq.
+	How to configure Authentication for ActiveMQ?  
	1. ``vi /data/activemq/conf/activemq.xml``  
	2. Insert `simpleAuthenticationPlugin` and corresponding content.  
	see http://activemq.apache.org/complex-single-broker-configuration-stomp-only.html  
	https://www.sleuthkit.org/autopsy/docs/user-docs/4.0/install_activemq.html    
	3. The broker block show be like:
 ```

	<broker xmlns="http://activemq.apache.org/schema/core" brokerName="localhost"dataDirectory="${activemq.data}">
		<destinationPolicy>
			...
		</destinationPolicy>
	
	
		<!--
			The managementContext is used to configure how ActiveMQ is exposed in
			JMX. By default, ActiveMQ uses the MBean server that is started by
			the JVM. For more information, see:
	
			http://activemq.apache.org/jmx.html
		-->
		<managementContext>
			<managementContext createConnector="false"/>
		</managementContext>
	
		<!--
			Configure message persistence for the broker. The default persistence
			mechanism is the KahaDB store (identified by the kahaDB tag).
			For more information, see:
	
			http://activemq.apache.org/persistence.html
		-->
		<persistenceAdapter>
			<kahaDB directory="${activemq.data}/kahadb"/>
		</persistenceAdapter>
	
	
		  <!--
			The systemUsage controls the maximum amount of space the broker will
			use before disabling caching and/or slowing down producers. For more information, see:
			http://activemq.apache.org/producer-flow-control.html
		  -->
		  <systemUsage>
			...
		</systemUsage>
	
		<!--
			The transport connectors expose ActiveMQ over a given protocol to
			clients and other brokers. For more information, see:
	
			http://activemq.apache.org/configuring-transports.html
		-->
		<transportConnectors>
			...
		</transportConnectors>
	
		<!-- destroy the spring context on shutdown to stop jetty -->
		<shutdownHooks>
			<bean xmlns="http://www.springframework.org/schema/beans" class="org.apache.activemq.hooks.SpringContextHook" />
		</shutdownHooks>
	
		<!-- 2017-5-17 19:51 -->
		<plugins>
			<simpleAuthenticationPlugin anonymousAccessAllowed="true">
				<users>
					<authenticationUser username="admin" password="admin" groups="producers,consumers,admins" />
					<authenticationUser username="frontend" password="test" groups="producers,consumers" />
				</users>
			</simpleAuthenticationPlugin>
		</plugins>
	</broker>
    
```
+	How to create a high availability cluster?  
	Reference: http://activemq.apache.org/clustering.html  
	<table>
		<tr><td>1</td><td>Queue consumer clusters</td></tr>
		<tr><td>2</td><td>Broker clusters</td></tr>
		<tr><td>3</td><td>Discovery of brokers</td></tr>
		<tr><td>4</td><td>Networks of brokers</td></tr>
		<tr><td>5</td><td>Master Slave</td></tr>
		<tr><td>6</td><td>Replicated Message Stores</td></tr>
	</table>
+	Master/Slave approach?  
	Reference: http://activemq.apache.org/masterslave.html  
	<table>
		<tr><td>1</td><td>Shared File System Master Slave</td></tr>
		<tr><td>2</td><td>JDBC Master Slave</td></tr>
		<tr><td>3</td><td>Replicated LevelDB Store</td></tr>
	</table>
+	How to preserve order of messages?  
	Reference: http://activemq.apache.org/how-do-i-preserve-order-of-messages.html
	<table>
		<tr><td>1</td><td>Exclusive Consumer</td></tr>
		<tr><td>2</td><td>Message Groups</td></tr>
	</table>  
+	How to configure JDBC Master Slave?
	Reference: https://my.oschina.net/xiaoxishan/blog/382502  
	http://stackoverflow.com/questions/34294621/activemq-failed-to-load-class-path-resource-activemq-xml/34294717  
	http://aorsoft.blog.51cto.com/2505763/498669  
	1. Install mysql and ensure it can be connected remotely.  
	2. `vi /data/activemq/conf/activemq.xml`  
	-	Insert dataSource(mysql-ds) configuration after broker  
	-	Modify persistenceAdapter(PS: `createTablesOnStartup` should be `false` once activemq instance has been started successfully because the corresponding tables will be initialized in database when createTablesOnStartup=true.)  
```

	<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd  http://activemq.apache.org/schema/core http://activemq.apache.org/schema/core/activemq-core.xsd">

	    <!-- Allows us to use system properties as variables in this configuration file -->
	    <bean class="org.springframework.beans.factory.config.PropertyPlaceholderConfigurer">
	        ...
	    </bean>
	
	   <!-- Allows accessing the server log -->
	    <bean id="logQuery" class="io.fabric8.insight.log.log4j.Log4jLogQuery"
	          lazy-init="false" scope="singleton"
	          init-method="start" destroy-method="stop">
	    </bean>
	
	    <!--
	        The <broker> element is used to configure the ActiveMQ broker.
	    -->
	    <broker xmlns="http://activemq.apache.org/schema/core" brokerName="localhost" dataDirectory="${activemq.data}">
	
	        <destinationPolicy>
	            ...
	        </destinationPolicy>
	
	
	        <!--
	            The managementContext is used to configure how ActiveMQ is exposed in
	            JMX. By default, ActiveMQ uses the MBean server that is started by
	            the JVM. For more information, see:
	
	            http://activemq.apache.org/jmx.html
	        -->
	        <managementContext>
	            <managementContext createConnector="false"/>
	        </managementContext>
	
	        <!--
	            Configure message persistence for the broker. The default persistence
	            mechanism is the KahaDB store (identified by the kahaDB tag).
	            For more information, see:
	
	            http://activemq.apache.org/persistence.html
	        -->
	        <persistenceAdapter>
	            <!--<kahaDB directory="${activemq.data}/kahadb"/>-->
	            <jdbcPersistenceAdapter dataDirectory="${activemq.data}" dataSource="#mysql-ds" createTablesOnStartup="false"/>
	        </persistenceAdapter>
	
	
	          <!--
	            The systemUsage controls the maximum amount of space the broker will
	            use before disabling caching and/or slowing down producers. For more information, see:
	            http://activemq.apache.org/producer-flow-control.html
	          -->
	          <systemUsage>
	            <systemUsage>
	                <memoryUsage>
	                    <memoryUsage percentOfJvmHeap="70" />
	                </memoryUsage>
	                <storeUsage>
	                    <storeUsage limit="100 gb"/>
	                </storeUsage>
	                <tempUsage>
	                    <tempUsage limit="50 gb"/>
	                </tempUsage>
	            </systemUsage>
	        </systemUsage>
	
	        <!--
	            The transport connectors expose ActiveMQ over a given protocol to
	            clients and other brokers. For more information, see:
	
	            http://activemq.apache.org/configuring-transports.html
	        -->
	        <transportConnectors>
	            ...
	        </transportConnectors>
	
	        <!-- destroy the spring context on shutdown to stop jetty -->
	        <shutdownHooks>
	            <bean xmlns="http://www.springframework.org/schema/beans" class="org.apache.activemq.hooks.SpringContextHook" />
	        </shutdownHooks>
	
	        <plugins>
	            ...
	        </plugins>
	
	    </broker>
	    <!-- 2017-5-19 11:16-->
	    <bean id="mysql-ds" class="org.apache.commons.dbcp2.BasicDataSource" destroy-method="close">
	        <property name="driverClassName" value="com.mysql.jdbc.Driver"/>
	        <property name="url" value="jdbc:mysql://192.168.68.24:3306/amq?relaxAutoCommit=true"/>
	        <property name="username" value="amq"/>
	        <property name="password" value="amq"/>
	        <property name="maxIdle" value="200"/>  
	        <property name="poolPreparedStatements" value="true"/>
	    </bean>
	
	    <!--
	        Enable web consoles, REST and Ajax APIs and demos
	        The web consoles requires by default login, you can disable this in the jetty.xml file
	
	        Take a look at ${ACTIVEMQ_HOME}/conf/jetty.xml for more details
	    -->
	    <import resource="jetty.xml"/>
	
	</beans>

```	
	3. Upload mysql-connector-java-5.1.26.jar into /data/activemq/lib/  
	