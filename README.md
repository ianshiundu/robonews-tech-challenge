# RoboVoice Technical Challenge
The challenge is to build an ​Imgur​ image uploading service that exposes a REST API.

## Appendix

### Running

To clone this project on your machine run this on your terminal:
```bash
git clone git@github.com:ianshiundu/robovoice-tech-challenge.git
```

You need to download and install sbt for this application to run.

Once you have sbt installed, running the following at the command prompt will start up Akka HTTP in development mode:

```bash
sbt run
```
Akka will start up on the HTTP port at <http://localhost:5000/>.   You don't need to deploy or reload anything.

You can also run the app using docker, by running:
```bash
sbt docker:publishLocal && docker run -p 8000:5000 dockerised-robovoice-challenge:0.2
```
Your dockerised app will be running on port  <http://localhost:8000/>

### Usage
To upload an image to Imgur, you make a POST request with the `Content-Type` specified to `application/json`.

Eg.
```bash
curl -X POST \
  http://localhost:5000/v1/images/upload \
  -H 'Content-Type: application/json' \
  -H 'cache-control: no-cache' \
  -d '{
	"urls": [
            "https://farm3.staticflickr.com/2879/11234651086_681b3c2c00_b_d.jpg",
            "https://farm4.staticflickr.com/3790/11244125445_3c2f32cd83_k_d.jpg"
        ]
}'
```
and get a Response like so:
```bash
{
    "jobId": "84e10410-26f9-43c3-adab-381ab446691f"
}
```

### Testing

To run the tests use the following command:


```bash
sbt test
```
or alternatively you can use your preferred IDE to run the tests.




![](https://media.giphy.com/media/l3vR9tEv3C2LrifsY/giphy.gif)
