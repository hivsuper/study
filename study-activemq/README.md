# study-activemq
## Environment 
ActiveMQ: apache-activemq-5.14.5  
OS: Ubuntu 15.10  
MySQL: 5.6.31-0ubuntu0.15.10.1  
JAVA: 1.7.0_101  
## Q&A  
+	How to integrate ActiveMQ with spring?  
	Refer to: http://elim.iteye.com/blog/1893038  
	http://elim.iteye.com/blog/1900937
+	How to install ActiveMQ?  
	1. Download software from http://activemq.apache.org/download.html.  
	2. Extract the files to /data/activemq.
+	How to start ActiveMQ up?  
	Entry /data/activemq/bin and run command `./activemq console` or `./activemq`
+	How to configure Authentication for ActiveMQ?  
	1. ``vi /data/activemq/conf/activemq.xml``  
	2. Insert `simpleAuthenticationPlugin` and corresponding content.  
	see http://activemq.apache.org/complex-single-broker-configuration-stomp-only.html  
	https://www.sleuthkit.org/autopsy/docs/user-docs/4.0/install_activemq.html    
	3. The broker block show be like:
 ```scheme

	<broker xmlns="http://activemq.apache.org/schema/core" brokerName="localhost"dataDirectory="${activemq.data}">
		....
	
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
	Assume there are two servers `192.168.68.23` and `192.168.68.24`. Configure them respectively as per steps below:  
	1. Install MySQL on `192.168.68.24` and ensure it can be connected remotely.  
		- `CREATE DATABASE amq;`  
		- `GRANT ALL PRIVILEGES ON amq.* TO amq@'%' IDENTIFIED BY 'amq';`
	2. Upload mysql-connector-java-5.1.26.jar into /data/activemq/lib/  
	3. `vi /data/activemq/conf/activemq.xml`  
		-	Insert dataSource(mysql-ds) configuration after broker  
		-	Modify persistenceAdapter(PS: `createTablesOnStartup` should be `false` once activemq instance has been started successfully because the corresponding tables will be initialized in database when createTablesOnStartup=true.)  
		Check the `mysql-ds` and `persistenceAdapter` configuration out:  
```scheme

	<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd  http://activemq.apache.org/schema/core http://activemq.apache.org/schema/core/activemq-core.xsd">

	    ...
	    
	    <broker xmlns="http://activemq.apache.org/schema/core" brokerName="localhost" dataDirectory="${activemq.data}">
	
	        ...
	        
	        <persistenceAdapter>
	            <!--<kahaDB directory="${activemq.data}/kahadb"/>-->
	            <jdbcPersistenceAdapter dataDirectory="${activemq.data}" dataSource="#mysql-ds" createTablesOnStartup="false"/>
	        </persistenceAdapter>
	
	        ...
	
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
	
	    ...
	
	</beans>

```		