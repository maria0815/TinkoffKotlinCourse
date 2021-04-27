package client.api

import client.dao.ClientDao
import client.model.Client
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiParam
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("clients")
class ClientController(private val clientDao: ClientDao) {

    @ApiOperation("Возвращает клиента с указанным id")
    @ApiResponses(
        value = [
            ApiResponse(code = 200, message = "Клиент найден", response = Client::class),
            ApiResponse(code = 404, message = "Клиент не найден")
        ]
    )
    @GetMapping("/{id}", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getClient(
        @ApiParam("Идентификатор клиента")
        @PathVariable id: Int
    ): ResponseEntity<Client> {
        val client = clientDao.getById(id) ?: return ResponseEntity(HttpStatus.NOT_FOUND)
        return ResponseEntity.ok(client)
    }
}