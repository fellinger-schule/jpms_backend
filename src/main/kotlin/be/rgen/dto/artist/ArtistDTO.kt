package be.rgen.dto.artist

import be.rgen.entity.Artist

class ArtistDTO {
    constructor(artist: Artist) { this.id = artist.id!!; this.name = artist.name }

    var id: Long = 0
    var name: String = ""
}