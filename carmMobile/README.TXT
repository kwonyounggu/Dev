//The following comes from http://stackoverflow.com/questions/13953123/blazeds-messaging-channel-connect-failed-error
In services-config.xml you should define your channel url.

url="http://{server.name}:{server.port}/{context.root}/messagebroker/amf"
to like this

url="/yourContextRootName/messagebroker/amf"
Do not define server.name and server.port, it comes with similar problem.

TO DO LIST (JAN-7-2015)
1. access limit in login-check
2. check onRemove() of mediators in order to remove the registered events -DONE
3. upmap in the context at the stage of shutdown function