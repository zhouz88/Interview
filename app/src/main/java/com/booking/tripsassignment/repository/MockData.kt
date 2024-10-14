package com.booking.tripsassignment.repository

import com.booking.tripsassignment.data.Hotel

enum class City(val id: Int, val cityname: String) {
    ROME(1, "Rome"),
    FLORENCE(2, "Florence"),
    MILAN(3, "Milan"),
    AMALFI(4, "Amalfi"),
    STOCKHOLM(5, "Stockholm"),
    GOTHENBURG(6, "Gothenburg"),
    NAPLES(7, "Naples"),
    PARIS(8, "Paris"),
    LYON(9, "Lyon"),
    NICE(10, "Nice"),
    DELHI(11, "Delhi"),
    AGRA(12, "Agra"),
    GOA(13, "Goa"),
    PONDICHERRY(14, "Pondicherry"),
    COCHIN(15, "Cochin"),
    KOVALAM(16, "Kovalam"),
    PUNE(17, "Pune")
}

fun City.currency(): String {
    return when (this) {
        City.DELHI, City.AGRA, City.GOA, City.PONDICHERRY, City.COCHIN, City.KOVALAM, City.PUNE -> "INR"
        else -> "EUR"
    }
}

fun City.hotels(): List<Hotel> {
    return when (this) {
        City.ROME -> listOf(
            build(
                name = "Rental in Rome Leonardo da Vinci",
                heroImageUrl = "https://cf.bstatic.com/xdata/images/hotel/max1024x768/187626544.jpg?k=468c0f0f16d479ae21acd280d2b0046606ac526c96b9e01a9617ec5c8c60df96&o=&hp=1"
            ),
            build(
                name = "Anantara Palazzo Naiadi",
                heroImageUrl = "https://cf.bstatic.com/xdata/images/hotel/max1024x768/299735318.jpg?k=a425f8d2380e34e706d4236d3c0380a59936e48a3243bf5c850a6e5adc2922fa&o=&hp=1"
            ),
            build(
                name = "Rome Cavalieri, A Waldorf Astoria Hotel",
                heroImageUrl = "https://cf.bstatic.com/xdata/images/hotel/max1024x768/107310894.jpg?k=f807205772905ce431ba60a627455bd6765cbf28cbadd607f864fcde1c89cac7&o=&hp=1"
            )
        )
        City.FLORENCE ->
            listOf(
                build(
                    name = "Firenze Camping In Town",
                    heroImageUrl = "https://cf.bstatic.com/xdata/images/hotel/max1024x768/208620626.jpg?k=e93b419894128698097727139e16d96b2222026c9edbdb06abda72e60d3031ab&o=&hp=1"
                ),

                build(
                    name = "Alfieri Signature Suites - Alfieri Collezione",
                    heroImageUrl = "https://cf.bstatic.com/xdata/images/hotel/max1024x768/222815328.jpg?k=ab3db29399733589fdb570b646618ee57ef1f8f27e05cc8ffd99b0dbfc18a68f&o=&hp=1"
                ),

                build(
                    name = "B&B Le Stanze del Duomo",
                    heroImageUrl = "https://cf.bstatic.com/xdata/images/hotel/max1024x768/66884088.jpg?k=a32f26d499241587b5bddab8f1ab30d58338a3f81702393e7f8654ad8f6a1036&o=&hp=1"
                ),
            )
        City.MILAN ->
            listOf(
                build(
                    name = "Rosa Grand Milano - Starhotels Collezione",
                    heroImageUrl = "https://cf.bstatic.com/xdata/images/hotel/max1024x768/264158980.jpg?k=9f4c03eb0c62962497b7b6d0137fda5b4cf52147a560f4849a01a724ab052ffb&o=&hp=1"
                ),

                build(
                    name = "Château Monfort - Relais & Châteaux",
                    heroImageUrl = "https://cf.bstatic.com/xdata/images/hotel/max1024x768/220059420.jpg?k=8d35789d99875f87ff72acd03fb67d166a38e3ede565ed517ca1520121f01874&o=&hp=11"
                ),

                build(
                    name = "Hotel Dei Cavalieri Milano Duomo",
                    heroImageUrl = "https://cf.bstatic.com/xdata/images/hotel/max1024x768/84555172.jpg?k=028ecdf3e14bb934fdf2a411004000dbcb6b6a94baaa2a13fbf41ff093b4c391&o=&hp=1"
                ),
            )
        City.AMALFI ->
            listOf(
                build(
                    name = "NH Collection Grand Hotel Convento di Amalfi",
                    heroImageUrl = "https://cf.bstatic.com/xdata/images/hotel/max1024x768/267009146.jpg?k=660368ae042748f93c7ccf44cb7b25dfbce8cb86bc259de4fda0813283c27019&o=&hp=1"
                ),

                build(
                    name = "Albergo Miramare Positano",
                    heroImageUrl = "https://cf.bstatic.com/xdata/images/hotel/max1024x768/52262839.jpg?k=063666f1d8734967fe65d6185a0750cc30b3212d10499c8c2651941c4a78ea47&o=&hp=1"
                ),

                build(
                    name = "Hotel Eden Roc Suites",
                    heroImageUrl = "https://cf.bstatic.com/xdata/images/hotel/max1024x768/143701975.jpg?k=ee6ab03d38f514dfb392ad95e6cc2c3be0aa5cf23b657348cf8f02140d0fcf0c&o=&hp=1"
                ),
            )

        City.STOCKHOLM ->
            listOf(
                build(
                    name = "Rosa Grand Milano - Starhotels Collezione",
                    heroImageUrl = "https://cf.bstatic.com/xdata/images/hotel/max1024x768/264158980.jpg?k=9f4c03eb0c62962497b7b6d0137fda5b4cf52147a560f4849a01a724ab052ffb&o=&hp=1"
                ),

                build(
                    name = "Château Monfort - Relais & Châteaux",
                    heroImageUrl = "https://cf.bstatic.com/xdata/images/hotel/max1024x768/220059420.jpg?k=8d35789d99875f87ff72acd03fb67d166a38e3ede565ed517ca1520121f01874&o=&hp=11"
                ),

                build(
                    name = "Hotel Dei Cavalieri Milano Duomo",
                    heroImageUrl = "https://cf.bstatic.com/xdata/images/hotel/max1024x768/84555172.jpg?k=028ecdf3e14bb934fdf2a411004000dbcb6b6a94baaa2a13fbf41ff093b4c391&o=&hp=1"
                ),
            )
        City.GOTHENBURG ->
            listOf(
                build(
                    name = "Radisson Blu Scandinavia Hotel, Göteborg Opens in new window",
                    heroImageUrl = "https://cf.bstatic.com/xdata/images/hotel/max1024x768/271320093.jpg?k=c0cc96e96386bfad343495073a5082626a7fcb6bdce39e3dcd219b1615d39a0d&o=&hp=1"
                ),

                build(
                    name = "Hotel Allén - Sure Hotel by Best Western Allen",
                    heroImageUrl = "https://cf.bstatic.com/xdata/images/hotel/max1024x768/206280685.jpg?k=66c9e40acff3c4f7379b6a958748e7295d3c4ff5eab6857617e34da96c35920d&o=&hp=1"
                ),

                build(
                    name = "Best Western Arena Hotel Gothenburg",
                    heroImageUrl = "https://cf.bstatic.com/xdata/images/hotel/max1024x768/199821928.jpg?k=c71acd120a0edc79cd193707c8a6a73ffa52e03b9c88142d3a505c86a189194d&o=&hp=1"
                )
            )

        City.NAPLES ->
            listOf(
                build(
                    name = "Hotel Royal Continental Opens in new window",
                    heroImageUrl = "https://cf.bstatic.com/xdata/images/hotel/max1024x768/161849540.jpg?k=2c096f406cf26edf2467d3a0651cf40f72e98ddc9d3012c2245cd3a0fbcb79a3&o=&hp=1"
                ),

                build(
                    name = "Romeo hotel",
                    heroImageUrl = "https://cf.bstatic.com/xdata/images/hotel/max1024x768/175892783.jpg?k=c46b1ddf293202d5d0cecaff4d0829926a00a0775eb0a4a7506a0d62cc3276f0&o=&hp=1"
                ),

                build(
                    name = "UNAHOTELS Napoli",
                    heroImageUrl = "https://cf.bstatic.com/xdata/images/hotel/max1024x768/264612160.jpg?k=3dacb0078c9ae4d772b69848df354b626f6ee32c343ed488d6e98c3ddff50cc4&o=&hp=1"
                )
            )
        City.PARIS ->
            listOf(
                build(
                    name = "Sweet Inn - Rivoli",
                    heroImageUrl = "https://cf.bstatic.com/xdata/images/hotel/max1024x768/95095762.jpg?k=92793eac1c10698135069793f23e53e0742f10e53f5ba3561cbf66ac07d1e2fd&o=&hp=1"
                ),

                build(
                    name = "GuestReady - Amazing Le Marais Flat - 2 mins to Hôtel de Ville",
                    heroImageUrl = "https://cf.bstatic.com/xdata/images/hotel/max1024x768/234830930.jpg?k=e88e6ad026903dbba6b2fad5a74d6842556e304dc6617a7f21cfbcf7eec36c9e&o=&hp=1"
                ),

                build(
                    name = "Hôtel Duo",
                    heroImageUrl = "https://cf.bstatic.com/xdata/images/hotel/max1024x768/53985098.jpg?k=f7e8534a072b80fa3ddab18196d453f54271b5592fb9dc24e4f9b5dc4f07592c&o=&hp=1"
                )
            )
        City.LYON ->
            listOf(
                build(
                    name = "Radisson Blu Hotel, Lyon",
                    heroImageUrl = "https://cf.bstatic.com/xdata/images/hotel/max1024x768/232806418.jpg?k=8edbc938f27b179dd035b1479349c275862e07a46fb4ce536f321596004a07f5&o=&hp=1"
                ),
                build(
                    name = "InterContinental Lyon - Hotel Dieu, an IHG Hotel",
                    heroImageUrl = "https://cf.bstatic.com/xdata/images/hotel/max1024x768/273893560.jpg?k=27ed048f98aa50041b5d5df98250f25d9a4a8ec9c3130423bf77786652cd790f&o=&hp=1"
                ),

                build(
                    name = "Fourvière Hôtel",
                    heroImageUrl = "https://cf.bstatic.com/xdata/images/hotel/max1024x768/84393422.jpg?k=c715bbe9aea2adcb59d7c559705a495f813d665aabb44a0bac7c7fd5c6a11526&o=&hp=1"
                )
            )
        City.NICE ->
            listOf(
                build(
                    name = "Hyatt Regency Nice Palais de la Méditerranée",
                    heroImageUrl = "https://cf.bstatic.com/xdata/images/hotel/max1024x768/111790860.jpg?k=acad9c653dba441e8269da99756df869da2153f47e6974650e658823fa0328d5&o=&hp=1"
                ),

                build(
                    name = "Hôtel West End Promenade",
                    heroImageUrl = "https://cf.bstatic.com/xdata/images/hotel/max1024x768/206507977.jpg?k=8725e831c5063e5481ac2c29391d7e26ae0de1c8070a475c2fe313dd703cbd06&o=&hp=1"
                ),

                build(
                    name = "Mercure Nice Promenade Des Anglais",
                    heroImageUrl = "https://cf.bstatic.com/xdata/images/hotel/max1024x768/18601630.jpg?k=530e65830e30b6b9734006c30f3d2b515abe2ad59a0e47e4ac742ff7ee40cc73&o=&hp=1"
                ),
            )

        City.DELHI ->
            listOf(
                build(
                    name = "Shangri-La Eros New Delhi",
                    heroImageUrl = "https://cf.bstatic.com/xdata/images/hotel/max1024x768/295686542.jpg?k=88737ca54ca3d4c7da9aa31a9bf881c4878f96fa8b392bd3fc65dfa637679ff5&o=&hp=1"
                ),

                build(
                    name = "Taj Palace, New Delhi",
                    heroImageUrl = "https://cf.bstatic.com/xdata/images/hotel/max1024x768/36573971.jpg?k=6be1b3bf57628f82ceb3ab04b7221e2d0675b8d6d66c9e09363990a8850cc952&o=&hp=1"
                ),

                build(
                    name = "Novotel New Delhi International Airport",
                    heroImageUrl = "https://cf.bstatic.com/xdata/images/hotel/max1024x768/252166334.jpg?k=3afa35f65d5ce907aa6545c6075879cd918d5c28d00626761ad453be453f01b7&o=&hp=1"
                ),
            )
        City.AGRA ->
            listOf(
                build(
                    name = "Radisson Hotel Agra ",
                    heroImageUrl = "https://cf.bstatic.com/xdata/images/hotel/max1024x768/275924210.jpg?k=aff80883933e51a3d126f6f403822a4cecd138338999a5d064955491ca2a864e&o=&hp=1"
                ),

                build(
                    name = "ITC Mughal, A Luxury Collection Resort & Spa, Agra",
                    heroImageUrl = "https://cf.bstatic.com/xdata/images/hotel/max1024x768/178838580.jpg?k=8d5b8a279d423be8b4ad0fe43a94d2e239d84638841ed70dcd0712a113e63586&o=&hp=1"
                ),

                build(
                    name = "The Oberoi Amarvilas Agra",
                    heroImageUrl = "https://cf.bstatic.com/xdata/images/hotel/max1024x768/47406117.jpg?k=2c0d45158285df03e79c683ea4a2d04328b4270b0ba1a80175e28f0710f627a2&o=&hp=1"
                ),
            )
        City.GOA ->
            listOf(
                build(
                    name = "Taj Fort Aguada Resort & Spa, Goa",
                    heroImageUrl = "https://cf.bstatic.com/xdata/images/hotel/max1024x768/59511743.jpg?k=b2a1143941d2de67941e55e685e5385c665792019fa43c972043769cc2091c5c&o=&hp=1"
                ),

                build(
                    name = "Taj Exotica Resort & Spa, Goa",
                    heroImageUrl = "https://cf.bstatic.com/xdata/images/hotel/max1024x768/145814506.jpg?k=18fb49c6cdaf938a5376cbfc1501ab020a52b1c6f542e481cb34d36045463eaa&o=&hp=1"
                ),

                build(
                    name = "The Leela Goa",
                    heroImageUrl = "https://cf.bstatic.com/xdata/images/hotel/max1024x768/34261038.jpg?k=51496304e6713360c3692a063336e555902a4afe01811b257a5b2d4cd42c14cc&o=&hp=1"
                ),
            )
        City.PUNE ->
            listOf(
                build(
                    name = "Novotel Pune Viman Nagar Road",
                    heroImageUrl = "https://cf.bstatic.com/xdata/images/hotel/max1024x768/252004905.jpg?k=69522f1462cbcc701c40921d8920876240fc5e7b0ef85a165180bc7806a689ea&o=&hp=1"
                ),

                build(
                    name = "The Corinthians Resort & Club",
                    heroImageUrl = "https://cf.bstatic.com/xdata/images/hotel/max1024x768/5515416.jpg?k=23173612e2cb0f73e7268877a334a05f7d0632e16512cebbd68b6da9064796ad&o=&hp=1"
                ),

                build(
                    name = "The Orchid Hotel Hinjewadi Pune",
                    heroImageUrl = "https://cf.bstatic.com/xdata/images/hotel/max1024x768/203742984.jpg?k=805c98d2fbf8f5e261b74606584aa68078a32278c26efa974227fa35ac770b6d&o=&hp=1"
                ),
            )
        City.PONDICHERRY ->
            listOf(
                build(
                    name = "Le Pondy",
                    heroImageUrl = "https://cf.bstatic.com/xdata/images/hotel/max1024x768/154934148.jpg?k=515ba840fc66dd1ecf21818fd389db7a009eb9bdc962a1b176ab63d46987b545&o=&hp=1"
                ),

                build(
                    name = "The Promenade",
                    heroImageUrl = "https://cf.bstatic.com/xdata/images/hotel/max1024x768/241431678.jpg?k=680b4a4775cc5304618fc8b4229125ca40ed342b4bab37b44d98fc63187a5dbf&o=&hp=1"
                ),

                build(
                    name = "Palais De Mahe - CGH Earth ",
                    heroImageUrl = "https://cf.bstatic.com/xdata/images/hotel/max1024x768/229001409.jpg?k=59a9752edb68d5c541344508957f7d95d22c0c05b76eeac5d05a8ff937e4bc51&o=&hp=1"
                ),
            )
        City.COCHIN ->
            listOf(
                build(
                    name = "Radisson Blu Kochi",
                    heroImageUrl = "https://cf.bstatic.com/xdata/images/hotel/max1024x768/232823150.jpg?k=54d5fd0fd91b3a6f7fff5adfdf8c83e77f6eda22de0acb0bbb77fc1142c87b0e&o=&hp=1"
                ),

                build(
                    name = "Holiday Inn Cochin, an IHG Hotel",
                    heroImageUrl = "https://cf.bstatic.com/xdata/images/hotel/max1024x768/244464342.jpg?k=ca55bf1c50957b34a88abcfd8e3d99ddfc2a76199bb05a24fce0445f32b3b1c5&o=&hp=1"
                ),

                build(
                    name = "The Leela Goa",
                    heroImageUrl = "https://cf.bstatic.com/xdata/images/hotel/max1024x768/34261038.jpg?k=51496304e6713360c3692a063336e555902a4afe01811b257a5b2d4cd42c14cc&o=&hp=1"
                ),
            )
        City.KOVALAM ->
            listOf(
                build(
                    name = "Taj Green Cove Resort and Spa Kovalam",
                    heroImageUrl = "https://cf.bstatic.com/xdata/images/hotel/max1024x768/210918969.jpg?k=fef6d0af1a5c153a72801a14eaa8eb5b43eacc76dc6135f9892b45ae31a24f27&o=&hp=1"
                ),

                build(
                    name = "The Raviz Kovalam",
                    heroImageUrl = "https://cf.bstatic.com/xdata/images/hotel/max1024x768/34223229.jpg?k=9826fa689c3fe157ca6b584a07c5456c6a241078e79250dd26db46f94ca77cfa&o=&hp=1"
                ),

                build(
                    name = "Niraamaya Retreats, Surya Samudra, Kovalam",
                    heroImageUrl = "https://cf.bstatic.com/xdata/images/hotel/max1024x768/205402347.jpg?k=dcdda37a2da98e5416e2b7527411242779afd04b0c8a7be86f8f44c96038f8db&o=&hp=1"
                ),
            )

    }
}

fun City.build(name: String, heroImageUrl: String): Hotel {
    return Hotel(
        id = "${(name + cityname).hashCode()}",
        name = name,
        mainPhoto = heroImageUrl,
        cityId = id,
        cityName = cityname
    )
}
