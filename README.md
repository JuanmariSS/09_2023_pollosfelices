# Pollos Felices

- git reset --hard HEAD
- git pull

## _Para acceder a Swagger_ ##

- http://localhost:8080/swagger-ui.html

## _Para acceder a la consola de H2_ ##

- http://localhost:8080/h2-console

## _Dependencias de JSP_ ##
		
		<dependency>
			<groupId>org.apache.tomcat.embed</groupId>
			<artifactId>tomcat-embed-jasper</artifactId>
			<scope>provided</scope>
		</dependency>

## _Dependencias de JSTL_ ##

		<dependency>
  			<groupId>jakarta.servlet.jsp.jstl</groupId>
  			<artifactId>jakarta.servlet.jsp.jstl-api</artifactId>
  		</dependency>
  		
		<dependency>
  			<groupId>org.glassfish.web</groupId>
  			<artifactId>jakarta.servlet.jsp.jstl</artifactId>
		</dependency>
