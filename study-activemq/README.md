# study-activemq
+	How to integrate ActiveMQ with spring?  
	Refer to: http://elim.iteye.com/blog/1893038  
	http://elim.iteye.com/blog/1900937
+	How to install ActiveMQ in Ubuntu 15.10?  
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
