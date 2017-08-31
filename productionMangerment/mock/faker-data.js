module.exports = function () {
    var faker = require("faker");
    faker.locale = "zh_CN";
    var _ = require("lodash");
    return {
        people: _.times(100, function (n) {
            return {
                id: n,
                name: faker.name.findName(),
                avatar: faker.internet.avatar()
            }
        }),
        address: _.times(100, function (n) {
            return {
                id: faker.random.uuid(),
                city: faker.address.city(),
                county: faker.address.country()
            }
        }),
        user: _.times(50, function (n) {
            return {
                id: n,
                name: faker.internet.userName(),
                contact: faker.random.phoneNumber,
                password: "admin",
                access: 1
            }
        }),
        order: _.times(20, function (n) {
            return {
                id: faker.random.uuid(),
                companyname: faker.company.companyName,
                workname: faker.lorem.sentence(),
                price: faker.random.number(),
                amount: faker.random.number(),

                pic: faker.image.business(),
                targettime: faker.date.future()

            }
        }),
        salesamountMonth: _.times(1, function (n) {
            return {
                guangyuangangjing: faker.random.number(),
                daileigangjing: faker.random.number(),
                niuzhuangangjing: faker.random.number(),
                bianxinggangjinzhi: faker.random.number(),
                bianxinggangjinyuan: faker.random.number()
            }
        }),
        salesamountYear: _.times(12, function () {
            return {
                guangyuangangjing: faker.random.number(),
                daileigangjing: faker.random.number(),
                niuzhuangangjing: faker.random.number(),
                bianxinggangjinzhi: faker.random.number(),
                bianxinggangjinyuan: faker.random.number()
            }
        }),
        menber: _.times(12, function (n) {
            return {
                id: faker.random.uuid(),
                companyname: faker.company.companyName(),
                contact: faker.random.phoneNumber,
                ownername: faker.internet.userName(),
                companyaddress: faker.address.city(),
            }
        }),
        material: _.times(30, function (n) {
            return {
                id: faker.random.uuid(),
                class: "原材料",
                amount: faker.random.number(),
                name: faker.internet.userName(),
                limit: faker.random.number(),
                order: 0
            }
        }),
        ordermaterial: _.times(20, function (n) {
            return {
                orderid: faker.random.uuid(),
                class: "原材料",
                status:'待入库',
                name: faker.internet.userName(),
                order: 0
            }
        }),
         materialout: _.times(1, function (n) {
            return {
                id: faker.random.uuid(),
                class: "原材料",
                status:'已出库',
                name: faker.internet.userName(),
                amount:faker.random.number(),
                time:faker.date.future(),
            }
        }),

    }
}