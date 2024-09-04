FROM ubuntu:latest
LABEL authors="Lucas"

ENTRYPOINT ["top", "-b"]