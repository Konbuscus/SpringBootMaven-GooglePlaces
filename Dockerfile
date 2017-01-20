FROM maven
ADD . /projet
WORKDIR /projet
RUN dir
CMD ["mvn", "spring-boot:run"]