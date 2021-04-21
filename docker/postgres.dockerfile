FROM postgres
ENV POSTGRES_USER hiworld
ENV POSTGRES_PASSWORD password
COPY docker/schema.sql /docker-entrypoint-initdb.d