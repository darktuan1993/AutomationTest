
FROM maven:3.9.4-eclipse-temurin-17

RUN apt-get update && apt-get install -y wget gnupg2 curl unzip \
    && wget https://dl.google.com/linux/direct/google-chrome-stable_current_amd64.deb \
    && apt install -y ./google-chrome-stable_current_amd64.deb \
    && rm -f google-chrome-stable_current_amd64.deb


RUN CHROME_DRIVER_VERSION=`curl -sS https://chromedriver.storage.googleapis.com/LATEST_RELEASE` && \
    wget -N https://chromedriver.storage.googleapis.com/${CHROME_DRIVER_VERSION}/chromedriver_linux64.zip && \
    unzip chromedriver_linux64.zip && \
    mv chromedriver /usr/bin/chromedriver && \
    chmod +x /usr/bin/chromedriver && \
    rm chromedriver_linux64.zip


WORKDIR /app
COPY . /app

CMD ["mvn", "clean", "test"]
