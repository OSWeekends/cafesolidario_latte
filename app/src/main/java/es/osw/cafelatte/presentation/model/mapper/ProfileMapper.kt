package es.osw.cafelatte.presentation.model.mapper

import com.google.firebase.auth.FirebaseUser
import es.osw.cafelatte.domain.model.Profile

class ProfileMapper {
    companion object {
        fun map(data: FirebaseUser?): Profile =
                Profile(name = data?.displayName,
                        image = data?.photoUrl.toString(),
                        email = data?.email!!)
    }
}
