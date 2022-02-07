package be.rgen.dto.album
import be.rgen.entity.Album

class AlbumDTO {
    constructor(album: Album) {
        this.id = album.id!!
        this.artistName = album.albumArtist.name
        this.artistId = album.albumArtist.id!!
        this.title = album.title
    }

    var id: Long = 0
    var artistName: String = ""
    var artistId: Long = 0
    var title: String = ""
}