package graeme.hosford.avatarcharacterdatabase.repo.layer.character.list

import graeme.hosford.avatarcharacterdatabase.entity.layer.CharacterEntity
import graeme.hosford.avatarcharacterdatabase.repo.layer.common.pagination.PaginatedRepo

const val CHARACTERS_PER_PAGE = 25

interface CharacterListRepo :
    PaginatedRepo<CharacterEntity>