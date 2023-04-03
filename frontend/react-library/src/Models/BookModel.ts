class BookModel {
    id: number;
    title: String;
    author?: String;
    description?: String;
    copies? : number;
    copiesAvailable? : number;
    category? : String;
    img? : string;
    constructor(id: number, 
                title: String, 
                author?: String, 
                description?: String, 
                copies? : number, 
                copiesAvailable? : number, 
                category? : String, 
                img? : string) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.description = description;
        this.copies = copies;
        this.copiesAvailable = copiesAvailable;
        this.category = category;
        this.img = img;
    }
}

export default BookModel;