package es.osw.cafelatte.data.model.mapper

import es.osw.cafelatte.data.model.ProfileDto
import es.osw.cafelatte.domain.model.Profile

class ProfileDtoMapper {
    companion object {
        fun map(profile: Profile): ProfileDto {
            return ProfileDto(email = profile.email,
                    name = profile.name,
                    image = profile.image)
        }
    }
}
