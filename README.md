# RoboVoice Technical Challenge
The challenge is to build an ​Imgur​ image uploading service that exposes a REST API.

## Appendix

### Running

To clone this project on your machine run this on your terminal:
```bash
git@github.com:ianshiundu/robovoice-tech-challenge.git
```

You need to download and install sbt for this application to run.

Once you have sbt installed, running the following at the command prompt will start up Play in development mode:

```bash
sbt run
```

Akka will start up on the HTTP port at <http://localhost:5000/>.   You don't need to deploy or reload anything.

### Usage
To upload an image to Imgur, you make a POST request with the `Content-Type` specified to `application/json`.

Eg.
```bash
curl -X POST \
  http://localhost:5000/v1/images/upload \
  -H 'Content-Type: application/json' \
  -H 'cache-control: no-cache' \
  -d '{
	"urls": "https://cdn1.carbuyer.co.uk/sites/carbuyer_d7/files/styles/16x9_720/public/2018/12/skoda-scala-front.jpg?itok=xj_WPkDb"
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
