FROM selenium/standalone-chrome:latest
USER root
RUN sudo apt-get update && apt-get install -y maven

WORKDIR /app
COPY . /app

CMD ["mvn", "clean", "test"]
