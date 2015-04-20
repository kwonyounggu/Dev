//Memory leak discussion on
http://forum.spring.io/forum/spring-projects/web/flex/80980-shutting-down-flex-message-broker-cleanly
for created a ThreadLocal with key of type [java.lang.ThreadLocal] (value [java.lang.ThreadLocal@79b249ae]) and a value of type [flex.messaging.io.SerializationContext] (value [flex.messaging.io.SerializationContext@28037ced]) but failed to remove it when the web application was stopped. Threads are going to be renewed over time to try and avoid a probable memory leak.

http://help.adobe.com/en_US/LiveCycleDataServicesES/4.7/Installing/lcds_install.html
http://blogs.infosupport.com/flash-builder-4-the-easiest-way-to-create-a-combined-flex-java-project/

#1.
What is load-on-startup
load on startup servlet tag exampleAs stated earlier load-on-startup is a tag element which appear inside <servlet> tag in web.xml. load-on-startup tells the web container about loading of a particular servlet. if you don't specify load-on-startup then container will load a particular servlet when it feels necessary most likely when first request for that servlet will come, this may lead to longer response time for that query if Servlet is making database connections or performing ldap authentication which contribute network latency or any other time consuming job, to avoid this, web container provides you a mean to specify certain servlet to be loaded during deployment time of application by using load-on-startup parameter.

If you specify load-on-startup parameter inside a servlet than based upon its value Container will load it.you can specify any value to this element but in case of load-on-startup>0 , servlet with less number will be loaded first. For example in below web.xml AuthenticationServlet will be loaded before AuthorizationServlet because load-on-startup value for AuthenticationServlet is less (2) while for AuthorizationServlet is 4.

load-on-startup Example in web.xml

here is an example of how to use load on startup tag inside servlet element in web.xml:

<servlet>
<servlet-name>AuthenticationServlet</servlet-name>
<display-name>AuthenticationServlet</display-name>
<servlet-class>com.trading.AuthenticationServlet</servlet-class>
<load-on-startup>2</load-on-startup>
</servlet>

<servlet>
<servlet-name>AuthorizationServlet</servlet-name>
<display-name>AuthorizationServlet</display-name>
<servlet-class>com.trading.AuthorizationServlet</servlet-class>
<load-on-startup>4</load-on-startup>
</servlet>

Important points on load-on-startup element

1. If <load-on-startup> value is same for two servlet than they will be loaded in an order on which they are declared inside web.xml file.
2. if <load-on-startup> is 0 or negative integer than Servlet will be loaded when Container feels to load them.
3. <load-on-startup> guarantees loading, initialization and call to init() method of servlet by web container.
4. If there is no <load-on-startup> element for any servlet than they will be loaded when web container decides to load them.

When to use <load-on-startup> in web.xml
<load-on-startup> is suitable for those servlet which performs time consuming jobs e.g. Creating Database Connection pool, downloading files or data from network or prepare environment ready for servicing client in terms of initializing cache , clearing pipelines and loading important data in memory. If any of your servlet performs these jobs then declare them using <load-on-startup> element and specify order as per your business logic or what suites your application. Remember lower the value of <load-on-startup>, servlet will be loaded first. You can also check your web container documentation on how exactly load on start-up is supported.


Read more: http://javarevisited.blogspot.com/2011/12/load-on-startup-servlet-webxml-example.html#ixzz2lyUcZkEr

--- Use BlazeDS.4.6.0 --- Jan 16, 2014