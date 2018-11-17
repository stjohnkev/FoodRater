package ie.ul.kevin_st_john.foodrater;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.FoodViewHolder>{

    private List<Food> mFoods = new ArrayList<>();
    private RecyclerView mRecyclerView;

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        mRecyclerView=recyclerView;
    }

    public void addFood(){
        //TODO - attempted
        Food newFood = new Food();
        mFoods.add(0, newFood);
        //newFood.getImageResourceId();

        notifyItemInserted(0);
        notifyItemRangeChanged(0, mFoods.size());
        //notifyDataSetChanged();
        mRecyclerView.scrollToPosition(0);

    }

    public void removeName(int index){
        mFoods.remove(index);
        notifyItemRemoved(index);
        notifyItemRangeChanged(0,mFoods.size());
    }

    @NonNull
    @Override
    public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //TODO - Attempted
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.food_item_view, parent, false);

        return new FoodViewHolder(itemView);


    }

    @Override
    public void onBindViewHolder(@NonNull FoodViewHolder holder, int position) {

        final Food food = mFoods.get(position);
        holder.mName.setText(food.getName());
        holder.mImageView.setImageResource(food.getImageResourceId());
        holder.mRatingBar.setRating(food.getRating());
        // TODO get food from array of foods



    }

    @Override
    public int getItemCount() {
        //TODO - Attempted
        return mFoods.size();
    }

    class FoodViewHolder extends RecyclerView.ViewHolder{

        private ImageView mImageView;
        private TextView mName;
        private RatingBar mRatingBar;

        public FoodViewHolder(@NonNull View itemView) {
            super(itemView);
            mImageView=itemView.findViewById(R.id.food_pic);
            mName=itemView.findViewById(R.id.name);
            mRatingBar=itemView.findViewById(R.id.rating_bar);

            //DONE Delete a food on a a long press
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    removeName(getAdapterPosition());
                    return true;
                }
            });


            // Done together - update the rating for this food
            mRatingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                @Override
                public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                    if (fromUser){
                        //update this foods rating
                        Food currentFood = mFoods.get(getAdapterPosition());
                        currentFood.setRating(rating);
                    }
                }
            });
        }
    }
}

