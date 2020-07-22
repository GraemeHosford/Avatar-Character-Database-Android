package graeme.hosford.avatarcharacterdatabase.repo.character.list

import graeme.hosford.avatarcharacterdatabase.entity.CharacterEntity
import graeme.hosford.avatarcharacterdatabase.repo.common.pagination.PaginatedRepo

const val CHARACTERS_PER_PAGE = 25

interface CharacterListRepo : PaginatedRepo<CharacterEntity>