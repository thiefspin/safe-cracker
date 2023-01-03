# Safe Cracker
Randomly makes guesses until it finds the provided password.

## Usage
### Simple
Set password as ENV variable as follows: `export password=verysecret` then `./gradlew run`
### Docker
* Build the image with: `docker build -t safe-cracker:0.0.1 .`
* Find the image id with `docker images` and run it as follows: `docker run -it --rm -e password=12345 image_id`. Note: replace `12345` with the actual password.
