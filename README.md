# HTTP-Denial-Of-service-client
Simple Java CLI HTTP Denial-of-Service protection system

The user enters the number of HTTP clients to simulate (as CLI args).
Each HTTP client does the following:
      - Send HTTP request to a server with client identifier as a query parameter.
      - After the request is done, wait some random time and then send another request (with same client id).
      - The HTTP clients run simultaneously (concurrently) without blocking each other.
      - The client runs until key press(+ Enter) after which it will gracefully drain all the requests (wait for all the of them to complete) and will exit.
