package be.rgen.dto.track

import be.rgen.entity.Album
import be.rgen.entity.Artist
import be.rgen.entity.Track
import com.fasterxml.jackson.annotation.JsonManagedReference
import javax.persistence.Column
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

class TrackDTO {
    constructor(track: Track) {
        this.id = track.id!!
        this.trackNo = track.trackno
        this.length = track.length
        this.artistName = track.artist.name
        this.artistId = track.artist.id!!
        this.title = track.title
        this.albumId = track.album.id!!
        this.albumTitle = track.album.title
        this.path = track.path
        this.mimeType = track.mimeType
    }

    var id: Long = 0
    var mimeType: String = ""
    var albumId: Long = 0
    var albumTitle: String = ""
    var title: String = ""
    var path: String = ""
    var artistId: Long = 0
    var artistName: String = ""
    var length: UInt = 0u
    var trackNo: Int = 1
}