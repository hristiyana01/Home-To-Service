class CreatePostModel {
  constructor(title, description, status, location, categoryId, userId, phoneNumber) {
    this.title = title;
    this.description = description;
    this.status = status;
    this.location = location;
    this.categoryId = categoryId;
    this.userId = userId;
    this.phoneNumber = phoneNumber;
  }
}

export default CreatePostModel;

// Usage example
//const createPostModel = new CreatePostModel('title', 'description', 'status', 'location', 'categoryId', 'userId', 'phoneNumber');