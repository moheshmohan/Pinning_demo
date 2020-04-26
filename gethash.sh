echo | openssl s_client -servername $1 -connect $1:443 | openssl x509 -pubkey -noout | openssl rsa -pubin -outform der | openssl dgst -sha256 -binary | openssl enc -base64 

exit 0
