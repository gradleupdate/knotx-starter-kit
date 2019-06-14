FROM knotx/knotx:2.0.0-RC2
LABEL maintainer="Knot.x Project"

COPY ./knotx /usr/local/knotx

HEALTHCHECK --interval=5s --timeout=2s --retries=12 \
  CMD curl --silent --fail localhost:8092/api/example || exit 1

CMD [ "knotx", "run-knotx" ]