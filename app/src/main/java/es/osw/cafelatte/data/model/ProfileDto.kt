package es.osw.cafelatte.data.model

class ProfileDto(val _id: Long = 0, val name: String?, val email: String = "", val image:
String?) :
        ProfileDtoModel {

    companion object {
        val FACTORY = ProfileDtoModel.Factory<ProfileDto>(ProfileDtoModel.Creator(::ProfileDto))

        val SELECT_ALL_MAPPER: ProfileDtoModel.Mapper<ProfileDto> = FACTORY.select_allMapper()
    }

    override fun _id(): Long {
        return _id
    }

    override fun name(): String? {
        return name
    }

    override fun email(): String {
        return email
    }

    override fun image(): String? {
        return image
    }

}
