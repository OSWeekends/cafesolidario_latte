package es.osw.cafelatte.data.model.mapper.domain

import es.osw.cafelatte.data.model.ProfileDto
import es.osw.cafelatte.domain.model.Profile

class ProfileMapper {
    companion object {
        fun map(data: ProfileDto): Profile {
            return Profile(name = data.name,
                    email = data.email,
                    image = data.image)
        }
    }
}
