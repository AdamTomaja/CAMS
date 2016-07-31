# CAMS
CAMS - CyderCode Application Monitoring Standard

# Description
This framework is created to simplify application monitoring procedures. 
The idea is to create some protocol standard, applications and libraries. These components will be used to track multiple applications life - alive or dead, uptime and other statistics.

# CAMS Components
* datamodel - common data model for all other components
* explorer - desktop application to track informations from different CAMS nodes
* node-client - java library for connectivity with CAMS node
* standalone-node - You can run this application as standalone jar or as war archive in tomcat. It exposes some http protocol which is used by node-client to track information about Your server/application
