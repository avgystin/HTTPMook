package com.example.MookHTTP.controllers;


import com.example.MookHTTP.services.App1Service;
import com.example.MookHTTP.services.DelayDynamicService;
import com.example.MookHTTP.services.DelayStaticService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api_v1")
public class RestApiController {
    private final DelayStaticService delayStaticService;
    private final App1Service app1Service;
    private final DelayDynamicService delayDynamicService;

    @GetMapping(path = "/get_1")
    public String get_1() {
        long startTime = System.currentTimeMillis();
        delayStaticService.applyDelay("get_1", startTime);
        return ("answer_get_1");
    }

    @GetMapping(path = "/get_2")
    public ResponseEntity<String> get_2() {
        long startTime = System.currentTimeMillis();
        // Вызываем сервис для применения задержки перед отправкой ответа
        // В метод передается идентификатор эндпоинта "get_3" для получения соответствующей задержки из конфигурации
        delayStaticService.applyDelay("get_2", startTime);
        // Формируем HTTP ответ с помощью билдера ResponseEntity
        return ResponseEntity
                .status(HttpStatus.OK)   // HttpStatus.OK = 200 - запрос выполнен успешно
                .body("answer_get_2");
    }

    @GetMapping(path = "/get_3")
    public ResponseEntity<String> get_3() {
        long startTime = System.currentTimeMillis();
        delayStaticService.applyDelay("get_3", startTime);
        return ResponseEntity
                .status(500)  // Устанавливаем HTTP статус код 500 (Internal Server Error - внутренняя ошибка сервера)
                .header("Contype", "appson")  // Добавляем кастомный заголовок "Contype" со значением "appson"
                .body("answer_get_3");  // Устанавливаем тело ответа - строку "answer_get_3"
    }



    @GetMapping(path = "/{path}")
    public ResponseEntity<String> get_5(@PathVariable("path") String path) {
        long startTime = System.currentTimeMillis();
        String servAnswer = app1Service.method1(path);
        delayDynamicService.applyDynamicDelay(path, startTime);
        return ResponseEntity.ok(servAnswer);
    }

}
