FROM postgres
COPY init.sql /docker-entrypoint-initdb.d/
ENV POSTGRES_PASSWORD 123456
EXPOSE 5432:5432