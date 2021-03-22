package service

import entity.Photo
import java.util.*

/**
 * Сервис для работы с фотографиями
 */
interface IPhotoService {
    /**
     * Возвращает фотографию по идентификатору [photoId]
     */
    fun getPhoto(photoId: Int): Photo

    /**
     * Загружает фотографию [photo], возвращает идентификатор сохраненной
     * фотографии
     */
    fun uploadPhoto(photo: Photo): Int

    /**
     * Возвращает список фотографий за дату [date], если [date] не передана, то возвращает
     * список всех фотографий. Фотографии отсортированны по времени съемки.
     */
    fun getPhotos(date: Date?): List<Photo>

    /**
     * Возвращает список фотографий, снятых в радиусе [radius] метров
     * от точки с координатами ([latitude], [longitude])
     */
    fun getPhotos(latitude: Double, longitude: Double, radius: Int): List<Photo>
}