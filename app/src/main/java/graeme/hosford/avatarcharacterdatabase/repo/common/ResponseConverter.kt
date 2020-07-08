package graeme.hosford.avatarcharacterdatabase.repo.common

interface ResponseConverter<Response, Entity> {
    fun toEntity(response: Response): Entity
}