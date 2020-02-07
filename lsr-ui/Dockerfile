FROM node:alpine AS build
WORKDIR /app
COPY . .
RUN npm install && \
    npm run build

FROM nginx:alpine
COPY nginx.conf /etc/nginx/conf.d/default.conf
COPY --from=build /app/dist/* /usr/share/nginx/html/