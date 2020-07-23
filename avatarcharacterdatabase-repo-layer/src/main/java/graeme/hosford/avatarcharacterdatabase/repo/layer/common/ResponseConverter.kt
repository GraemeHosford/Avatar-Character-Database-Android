package graeme.hosford.avatarcharacterdatabase.repo.layer.common

interface ResponseConverter<Response, Entity> {
    fun toEntity(response: Response): Entity
}