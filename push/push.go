package main

import (
	"fmt"
	"log"

	"golang.org/x/net/context"

	firebase "firebase.google.com/go"
	_ "firebase.google.com/go/auth"

	"firebase.google.com/go/messaging"
	"google.golang.org/api/option"
)

func main() {
	ctx := context.Background()
	opt := option.WithCredentialsFile("service-account.json")
	app, err := firebase.NewApp(ctx, nil, opt)
	if err != nil {
		panic(err)
	}
	client, _ := app.Messaging(ctx)
	// The topic name can be optionally prefixed with "/topics/".
	token := os.Getenv("TOKEN")

	// See documentation on defining a message payload.
	message := &messaging.Message{
		Data: map[string]string{
			"title": "hoge",
			"body":  "yes!!",
		},
		Token: token,
	}

	// Send a message to the devices subscribed to the provided topic.
	response, err := client.Send(ctx, message)
	if err != nil {
		log.Fatalln(err)
	}
	// Response is a message ID string.
	fmt.Println("Successfully sent message:", response)
}
