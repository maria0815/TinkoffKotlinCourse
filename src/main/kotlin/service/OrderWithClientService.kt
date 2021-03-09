package service

import dao.ClientDAO
import dao.OrderDAO
import entity.OrderWithClient

/**
 * Сервис для работы с заказами и клиентами.
 */
class OrderWithClientService {

    private val clientDAO = ClientDAO()
    private val orderDAO = OrderDAO()

    /**
     * Возвращает список всем заказов с клиентами.
     */
    fun getAll(): List<OrderWithClient> {
        return orderDAO
            .findAll()
            .map { OrderWithClient(it, clientDAO.findByClientId(it.clientId)) }
    }

    /**
     * Возвращает отсортированный по полю [OrderWithClient.clientId] список
     * заказов с клиентами.
     */
    fun getSortedByClientId(): List<OrderWithClient> = this.getAll().sortedBy { it.clientId }

    /**
     * Возвращает сгруппированный по полю [OrderWithClient.price] список
     * заказов с клиентами.
     */
    fun getGroupedByPrice(): Map<Double, List<OrderWithClient>> = this.getAll().groupBy { it.price }

    /**
     * Возвращает количество заказов для которых выполняется [predicate].
     */
    fun getNumberOfFilteredOrders(predicate: (OrderWithClient) -> Boolean): Int {
        return this.getAll()
            .count { predicate(it) }
    }
}