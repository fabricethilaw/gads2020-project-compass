package com.thilawfabrice.compass.framework

import com.google.firebase.firestore.FirebaseFirestore
import com.thilawfabrice.compass.Compass
import com.thilawfabrice.compass.data.PersistenceSource
import com.thilawfabrice.compass.domain.entities.Author
import com.thilawfabrice.compass.domain.entities.TipForRemoteWork


class TipsStore(private val db: FirebaseFirestore) : PersistenceSource {
    private val tips = mutableListOf<TipForRemoteWork>()
    override fun loadTips(
        callback: (tips: List<TipForRemoteWork>) -> Unit,
        errorHandler: (String) -> Unit
    ) {
        db.collection(Compass.FIREBASE_TIPS_URL)
            .get()
            .addOnSuccessListener { result ->
                result.map { document ->
                    document.toObject(TipForRemoteWork::class.java)
                }.let {
                    tips.addAll(it)
                    callback(tips)
                }
            }

            .addOnFailureListener { exception ->
                exception.message?.let { errorHandler.invoke(it) }
                //  Log.d(TAG, "Error getting documents: ", exception)
            }
    }

    override fun getFeaturedTips(callback: (List<TipForRemoteWork>) -> Unit) {
        tips.filter { it.topics.contains("Featured") }.run {
            callback(this)
        }
    }

    override fun getSomeTipsForSpecificCategory(
        count: Int,
        callback: (List<TipForRemoteWork>) -> Unit
    ) {
        TODO("Not yet implemented")
    }

    override fun getAllTipsForSpecificCategory(
        category: String,
        callback: (List<TipForRemoteWork>) -> Unit
    ) {
        val r = tips.filter { it.topics.contains(category) }
        callback(r)
    }

    override fun saveNewTip(content: String, authorName: String, authorPicture: String) {
        TODO("Not yet implemented")
    }


    val tips_: List<TipForRemoteWork> = mutableListOf<TipForRemoteWork>().run {
        TipForRemoteWork(
            id = "1",
            content = "Slack integration to spin up ad hoc meetings. When you're working remotely, one of the hardest things to replicate are those side conversations, quick chats, bump-into-you in the hallway type discussions that can be really powerful to fix a problem or get a few heads on something in the moment. Spinning up Zoom meetings quickly really helps there.",
            author = Author(
                name = "Kim Gardner",
                picture = "https://pbs.twimg.com/profile_images/891765687861039108/JEtXB6tB_400x400.jpg",
                role = "Director of Engineering, Coral Vox Media"
            ),
            topics = arrayListOf("Zoom", "Featured")
        ).also { add(it) }

        TipForRemoteWork(
            id = "2",
            content = "Slack integration to spin up ad hoc meetings. When you're working remotely, one of the hardest things to replicate are those side conversations, quick chats, bump-into-you in the hallway type discussions that can be really powerful to fix a problem or get a few heads on something in the moment. Spinning up Zoom meetings quickly really helps there.",
            author = Author(
                name = "Labert Georges",
                picture = "https://pbs.twimg.com/profile_images/891765687861039108/JEtXB6tB_400x400.jpg",
                role = "Director of Engineering, Coral Vox Media"
            ),
            topics = arrayListOf("Zoom", "Featured")
        ).also { add(it) }

        TipForRemoteWork(
            id = "3",
            content = "Slack integration to spin up ad hoc meetings. When you're working remotely, one of the hardest things to replicate are those side conversations, quick chats, bump-into-you in the hallway type discussions that can be really powerful to fix a problem or get a few heads on something in the moment. Spinning up Zoom meetings quickly really helps there.",
            author = Author(
                name = "Marc Lebrun",
                picture = "https://pbs.twimg.com/profile_images/891765687861039108/JEtXB6tB_400x400.jpg",
                role = "Director of Engineering, Coral Vox Media"
            ),
            topics = arrayListOf("Zoom", "Featured")
        ).also { add(it) }

        TipForRemoteWork(
            id = "4",
            content = "Slack integration to spin up ad hoc meetings. When you're working remotely, one of the hardest things to replicate are those side conversations, quick chats, bump-into-you in the hallway type discussions that can be really powerful to fix a problem or get a few heads on something in the moment. Spinning up Zoom meetings quickly really helps there.",
            author = Author(
                name = "Marc Lebrun",
                picture = "https://pbs.twimg.com/profile_images/891765687861039108/JEtXB6tB_400x400.jpg",
                role = "Director of Engineering, Coral Vox Media"
            ),
            topics = arrayListOf("Zoom", "Featured")
        ).also { add(it) }

        TipForRemoteWork(
            id = "4",
            content = "Slack integration to spin up ad hoc meetings. When you're working remotely, one of the hardest things to replicate are those side conversations, quick chats, bump-into-you in the hallway type discussions that can be really powerful to fix a problem or get a few heads on something in the moment. Spinning up Zoom meetings quickly really helps there.",
            author = Author(
                name = "Marc Lebrun",
                picture = "https://pbs.twimg.com/profile_images/891765687861039108/JEtXB6tB_400x400.jpg",
                role = "Director of Engineering, Coral Vox Media"
            ),
            topics = arrayListOf("Zoom", "Featured")
        ).also { add(it) }

        TipForRemoteWork(
            id = "4",
            content = "Slack integration to spin up ad hoc meetings. When you're working remotely, one of the hardest things to replicate are those side conversations, quick chats, bump-into-you in the hallway type discussions that can be really powerful to fix a problem or get a few heads on something in the moment. Spinning up Zoom meetings quickly really helps there.",
            author = Author(
                name = "Marc Lebrun",
                picture = "https://pbs.twimg.com/profile_images/891765687861039108/JEtXB6tB_400x400.jpg",
                role = "Director of Engineering, Coral Vox Media"
            ),
            topics = arrayListOf("Zoom", "Featured")
        ).also { add(it) }

        TipForRemoteWork(
            id = "4",
            content = "Slack integration to spin up ad hoc meetings. When you're working remotely, one of the hardest things to replicate are those side conversations, quick chats, bump-into-you in the hallway type discussions that can be really powerful to fix a problem or get a few heads on something in the moment. Spinning up Zoom meetings quickly really helps there.",
            author = Author(
                name = "Marc Lebrun",
                picture = "https://pbs.twimg.com/profile_images/891765687861039108/JEtXB6tB_400x400.jpg",
                role = "Director of Engineering, Coral Vox Media"
            ),
            topics = arrayListOf("Zoom")
        ).also { add(it) }

        TipForRemoteWork(
            id = "4",
            content = "Slack integration to spin up ad hoc meetings. When you're working remotely, one of the hardest things to replicate are those side conversations, quick chats, bump-into-you in the hallway type discussions that can be really powerful to fix a problem or get a few heads on something in the moment. Spinning up Zoom meetings quickly really helps there.",
            author = Author(
                name = "Marc Lebrun",
                picture = "https://pbs.twimg.com/profile_images/891765687861039108/JEtXB6tB_400x400.jpg",
                role = "Director of Engineering, Coral Vox Media"
            ),
            topics = arrayListOf("Zoom", "Featured")
        ).also { add(it) }

        TipForRemoteWork(
            id = "4",
            content = "Slack integration to spin up ad hoc meetings. When you're working remotely, one of the hardest things to replicate are those side conversations, quick chats, bump-into-you in the hallway type discussions that can be really powerful to fix a problem or get a few heads on something in the moment. Spinning up Zoom meetings quickly really helps there.",
            author = Author(
                name = "Marc Lebrun",
                picture = "https://pbs.twimg.com/profile_images/891765687861039108/JEtXB6tB_400x400.jpg",
                role = "Director of Engineering, Coral Vox Media"
            ),
            topics = arrayListOf("Zoom", "Featured")
        ).also { add(it) }

        TipForRemoteWork(
            id = "4",
            content = "Slack integration to spin up ad hoc meetings. When you're working remotely, one of the hardest things to replicate are those side conversations, quick chats, bump-into-you in the hallway type discussions that can be really powerful to fix a problem or get a few heads on something in the moment. Spinning up Zoom meetings quickly really helps there.",
            author = Author(
                name = "Marc Lebrun",
                picture = "https://pbs.twimg.com/profile_images/891765687861039108/JEtXB6tB_400x400.jpg",
                role = "Director of Engineering, Coral Vox Media"
            ),
            topics = arrayListOf("Zoom", "Featured")
        ).also { add(it) }

        TipForRemoteWork(
            id = "4",
            content = "Slack integration to spin up ad hoc meetings. When you're working remotely, one of the hardest things to replicate are those side conversations, quick chats, bump-into-you in the hallway type discussions that can be really powerful to fix a problem or get a few heads on something in the moment. Spinning up Zoom meetings quickly really helps there.",
            author = Author(
                name = "Marc Lebrun",
                picture = "https://pbs.twimg.com/profile_images/891765687861039108/JEtXB6tB_400x400.jpg",
                role = "Director of Engineering, Coral Vox Media"
            ),
            topics = arrayListOf("Zoom", "Featured")
        ).also { add(it) }

        TipForRemoteWork(
            id = "4",
            content = "Slack integration to spin up ad hoc meetings. When you're working remotely, one of the hardest things to replicate are those side conversations, quick chats, bump-into-you in the hallway type discussions that can be really powerful to fix a problem or get a few heads on something in the moment. Spinning up Zoom meetings quickly really helps there.",
            author = Author(
                name = "Marc Lebrun",
                picture = "https://pbs.twimg.com/profile_images/891765687861039108/JEtXB6tB_400x400.jpg",
                role = "Director of Engineering, Coral Vox Media"
            ),
            topics = arrayListOf("Zoom", "Featured")
        ).also { add(it) }

        TipForRemoteWork(
            id = "4",
            content = "Slack integration to spin up ad hoc meetings. When you're working remotely, one of the hardest things to replicate are those side conversations, quick chats, bump-into-you in the hallway type discussions that can be really powerful to fix a problem or get a few heads on something in the moment. Spinning up Zoom meetings quickly really helps there.",
            author = Author(
                name = "Marc Lebrun",
                picture = "https://pbs.twimg.com/profile_images/891765687861039108/JEtXB6tB_400x400.jpg",
                role = "Director of Engineering, Coral Vox Media"
            ),
            topics = arrayListOf("Zoom", "Featured")
        ).also { add(it) }

        TipForRemoteWork(
            id = "4",
            content = "Slack integration to spin up ad hoc meetings. When you're working remotely, one of the hardest things to replicate are those side conversations, quick chats, bump-into-you in the hallway type discussions that can be really powerful to fix a problem or get a few heads on something in the moment. Spinning up Zoom meetings quickly really helps there.",
            author = Author(
                name = "Marc Lebrun",
                picture = "https://pbs.twimg.com/profile_images/891765687861039108/JEtXB6tB_400x400.jpg",
                role = "Director of Engineering, Coral Vox Media"
            ),
            topics = arrayListOf("Zoom", "Featured")
        ).also { add(it) }

        TipForRemoteWork(
            id = "4",
            content = "Slack integration to spin up ad hoc meetings. When you're working remotely, one of the hardest things to replicate are those side conversations, quick chats, bump-into-you in the hallway type discussions that can be really powerful to fix a problem or get a few heads on something in the moment. Spinning up Zoom meetings quickly really helps there.",
            author = Author(
                name = "Marc Lebrun",
                picture = "https://pbs.twimg.com/profile_images/891765687861039108/JEtXB6tB_400x400.jpg",
                role = "Director of Engineering, Coral Vox Media"
            ),
            topics = arrayListOf("Zoom", "Featured")
        ).also { add(it) }

        TipForRemoteWork(
            id = "4",
            content = "Slack integration to spin up ad hoc meetings. When you're working remotely, one of the hardest things to replicate are those side conversations, quick chats, bump-into-you in the hallway type discussions that can be really powerful to fix a problem or get a few heads on something in the moment. Spinning up Zoom meetings quickly really helps there.",
            author = Author(
                name = "Marc Lebrun",
                picture = "https://pbs.twimg.com/profile_images/891765687861039108/JEtXB6tB_400x400.jpg",
                role = "Director of Engineering, Coral Vox Media"
            ),
            topics = arrayListOf("Zoom", "Featured")
        ).also { add(it) }
        this
    }

}