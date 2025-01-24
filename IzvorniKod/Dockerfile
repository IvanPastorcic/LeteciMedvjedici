FROM nginx:alpine

# Copy the build folder contents into nginx's html directory in the Docker container
COPY build /usr/share/nginx/html

# Expose port 80 for nginx
EXPOSE 80

# Copy the Nginx configuration file
COPY nginx.conf /etc/nginx/conf.d/default.template

# Set the entrypoint for the container to substitute environment variables
ENTRYPOINT ["/bin/sh", "-c", "envsubst '${BACKEND_URL}' < /etc/nginx/conf.d/default.template > /etc/nginx/conf.d/default.conf && nginx -g 'daemon off;'"]
