package br.com.felipeborges.picpay_desafio_backend.notification;

import br.com.felipeborges.picpay_desafio_backend.transaction.Transaction;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class NotificationConsumer {
    private RestClient restClient;

    public NotificationConsumer(RestClient.Builder builder){
        this.restClient = builder
                .baseUrl("https://run.mocky.io/v3/5794d450-d2e2-4412-8131-73d0293ac1cc")
                .build();
    }

    @KafkaListener(topics = "transaction-notification", groupId = "picpay-desafio-backend")
    public void receiveNotification(Transaction transaction){

        var response = restClient.get()
                .retrieve()
                .toEntity(Notification.class);
        if (response.getStatusCode().isError() || !response.getBody().message()){
            throw new NotificationException("Error sending notification");
        }
    }
}
