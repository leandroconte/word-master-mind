FROM openjdk:11-jdk
EXPOSE 8000:8000
RUN mkdir /app
COPY ./build/install/word-master-mind/ /app/
WORKDIR /app/bin
CMD ["./word-master-mind"]
