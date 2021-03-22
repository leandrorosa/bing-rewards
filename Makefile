
build:
	./mvnw clean tidy:pom package jib:dockerBuild
