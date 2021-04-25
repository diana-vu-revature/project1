FROM gradle as gradle_build
WORKDIR /app
COPY . .
RUN gradle build

FROM tomcat:jdk11-openjdk
WORKDIR /usr/local/tomcat/webapps/
COPY --from=gradle_build /app/build/libs/ROOT.war .