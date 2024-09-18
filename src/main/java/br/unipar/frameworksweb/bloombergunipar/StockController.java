package br.unipar.frameworksweb.bloombergunipar;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Controller
@EnableWebSocketMessageBroker
public class StockController {

    private final Random random = new Random();
    private final List<Stock> stocks = new ArrayList<>();


    @Autowired
    private SimpMessagingTemplate messagingTemplate;


    // Inicializa 30 ações com valores fictícios
    @PostConstruct
    private void initStocks() {
        for (int i = 1; i <= 30; i++) {
            String name = generateRandomName();
            stocks.add(new Stock("Stock " + name, random.nextDouble() * 100));
            System.out.println("Stock " + name + " initialized with price: " + stocks.get(i - 1).getPrice());
        }
        new Thread(this::updateStockPrices).start();
    }

    private String generateRandomName() {
        StringBuilder name = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            name.append((char) (random.nextInt(26) + 'A'));
        }
        name.append(random.nextInt(10)); // Add a random digit
        return name.toString();
    }

    @MessageMapping("/connect")
    @SendTo("/topic/stock-prices")
    public List<Stock> connect(ConnectMessage message) {
        return stocks;
    }

    // Atualiza as ações e envia os dados quando uma mensagem é recebida

    public void updateStockPrices() {
        while (true) {
            try {
                for (Stock stock : stocks) {
                    stock.setPrice(stock.getPrice() + (random.nextDouble() - 0.5) * 2);  // Atualização aleatória
                    System.out.println("Stock " + stock.getName() + " updated to price: " + stock.getPrice());
                }
                messagingTemplate.convertAndSend("/topic/stock-prices", stocks);
                Thread.sleep(1000); // Update every second
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }

     //   return stocks;  // Retorna a lista de ações atualizada
    }
}
