package com.base.qinxd.library.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.base.qinxd.library.R;
import com.base.qinxd.library.holder.EmptyHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：qinxudong
 * <p>
 * 时间：16/1/8 15:58
 * <p>
 * 邮箱：qinxd1988@163.com
 * <p>
 * 描述：RecyclerView.Adapter基础类包括header，content，footer
 *
 * @param <H>
 * @param <C>
 * @param <F>
 */
public abstract class HeaderFooterRecyclerViewAdapter<H, C, F> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int VIEW_TYPE_MAX_COUNT = 1000;
    private static final int HEADER_VIEW_TYPE_OFFSET = 0;
    private static final int FOOTER_VIEW_TYPE_OFFSET = HEADER_VIEW_TYPE_OFFSET + VIEW_TYPE_MAX_COUNT;
    private static final int CONTENT_VIEW_TYPE_OFFSET = FOOTER_VIEW_TYPE_OFFSET + VIEW_TYPE_MAX_COUNT;

    public static final int STATE_HEADER = -1;

    public static final int STATE_CONTNET = -2;

    public static final int STATE_FOOTER = -3;

    private int headerItemCount;
    private int contentItemCount;
    private int footerItemCount;

    private boolean isEmpty;

    private String emptyDes = "内容为空";

    private int emptyImg = R.drawable.ic_gray_empty;

    protected OnItemClickListener onItemClickListener;

    public ArrayList<H> mHeaderList = new ArrayList<H>();

    public ArrayList<C> mContentList = new ArrayList<C>();

    public ArrayList<F> mFooterList = new ArrayList<F>();

    public Context mContext;

    public HeaderFooterRecyclerViewAdapter(Context context) {

        this.mContext = context;

    }

    public OnItemClickListener getOnItemClickListener() {

        return onItemClickListener;

    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {

        this.onItemClickListener = onItemClickListener;

    }

    /**
     * 添加header数据
     *
     * @param list
     */
    @Deprecated
    public void appendDataToHeaderList(List<H> list) {

        appendListDataToHeaderList(list, false);

    }

    /**
     * 添加header数据
     *
     * @param list
     */
    public void appendListDataToHeaderList(List<H> list) {

        appendListDataToHeaderList(list, false);

    }

    /**
     * 添加header数据
     *
     * @param list
     * @param isTop
     */
    public void appendListDataToHeaderList(List<H> list, boolean isTop) {

        if (list == null) {

            return;

        }

        synchronized (mHeaderList) {

            int size = mHeaderList.size();

            mHeaderList.ensureCapacity(size + list.size());

            if (isTop) {

                size = 0;

                mHeaderList.addAll(size, list);

            } else {

                mHeaderList.addAll(list);

            }

            notifyDataSetChanged();

        }

    }

    /**
     * 添加header至顶部
     *
     * @param list
     */
    public void appendListDataToHeaderTopList(List<H> list) {

        appendListDataToHeaderList(list, true);

    }

    /**
     * 添加 单个 header
     *
     * @param h
     * @param isTop
     */
    public void appendItemDataToHeaderList(H h, boolean isTop) {

        if (h == null) {

            return;

        }

        synchronized (mHeaderList) {

            int size = mHeaderList.size();

            if (isTop) {

                size = 0;

                mHeaderList.add(size, h);

            } else {

                mHeaderList.add(h);

            }

            notifyDataSetChanged();

        }

    }

    /**
     * 添加单条 header
     *
     * @param h
     */
    public void appendItemDataToHeaderList(H h) {

        appendItemDataToHeaderList(h, false);

    }

    /**
     * 添加单条 到最顶部
     *
     * @param h
     */
    public void appendItemDataToHeaderTopList(H h) {

        appendItemDataToHeaderList(h, true);

    }

    /**
     * 添加content 列表数据
     *
     * @param list
     */
    public void appendListDataToHeadListLocation(List<H> list, int position) {

        if (list == null) {

            return;

        }

        if (position > mHeaderList.size()) {

            return;

        }

        synchronized (mHeaderList) {

            int size = mHeaderList.size();

            mHeaderList.ensureCapacity(size + list.size());

            mHeaderList.addAll(position, list);

//            notifyHeaderItemRangeChanged(position, list.size());


            notifyDataSetChanged();

        }

    }

    /**
     * 刷新header数据
     *
     * @param list
     */
    public void refreshHeaderList(List<H> list) {

        if (list == null) {

            return;

        }

        mHeaderList.clear();

        synchronized (mHeaderList) {

            mHeaderList.ensureCapacity(list.size());

            mHeaderList.addAll(list);

            notifyDataSetChanged();

        }

    }

    /**
     * 添加普通数据
     *
     * @param list
     */
    @Deprecated
    public void appendDataToContentList(List<C> list) {

        appendListDataToContentListLocation(list, mContentList.size());

    }

    /**
     * 添加content 列表数据
     *
     * @param list
     */
    public void appendListDataToContentListLocation(List<C> list, int position) {

        if (list == null) {

            return;

        }

        if (position > mContentList.size()) {

            return;

        }

        synchronized (mContentList) {

            mContentList.ensureCapacity(mContentList.size() + list.size());

            mContentList.addAll(position, list);

//            notifyContentItemRangeChanged(position, list.size());

            notifyDataSetChanged();

        }

    }

    /**
     * 添加content 数据至列表底部
     *
     * @param list
     */
    public void appendListDataToContentList(List<C> list) {

        appendListDataToContentListLocation(list, mContentList.size());

    }

    /**
     * 添加content 数据至列表顶部
     *
     * @param list
     */
    public void appendListDataToContentTopList(List<C> list) {

        appendListDataToContentListLocation(list, 0);

    }

    /**
     * 将数据添加是列表顶部
     *
     * @param list
     */
    @Deprecated
    public void appendDataToTopContentList(List<C> list) {

        appendListDataToContentTopList(list);

    }

    @Deprecated
    public void appendItemDataToContent(C c) {

        appendItemDataToContentList(c, false);

    }

    @Deprecated
    public void appendItemDataToContentTop(C c) {

        appendItemDataToContentList(c, true);

    }

    /**
     * 添加单条content数据
     *
     * @param c
     * @param isTop
     */
    public void appendItemDataToContentList(C c, boolean isTop) {

        if (c == null) {

            return;

        }

        synchronized (mContentList) {

            int size = mContentList.size();

            if (isTop) {

                size = 0;

                mContentList.add(size, c);

            } else {

                mContentList.add(c);

            }

            notifyDataSetChanged();

        }

    }

    public void appendItemDataToContentList(C c, int position) {

        if (c == null) {

            return;

        }

        synchronized (mContentList) {

            int size = mContentList.size();

            if (position < size) {

                size = position;

                mContentList.add(position, c);

            } else {

                mContentList.add(c);

            }

            notifyDataSetChanged();

        }

    }

    /**
     * 添加单条content数据至底部
     *
     * @param c
     */
    public void appendItemDataToContentList(C c) {

        appendItemDataToContentList(c, false);

    }

    /**
     * 添加单条content数据至顶部
     *
     * @param c
     */
    public void appendItemDataToContentTopList(C c) {

        appendItemDataToContentList(c, true);

    }

    /**
     * 刷新内容
     *
     * @param list
     */
    public void refreshContentList(List<C> list) {

        if (list == null) {

            return;

        }

        mContentList.clear();

        synchronized (mContentList) {

            mContentList.ensureCapacity(list.size());

            mContentList.addAll(list);

            notifyDataSetChanged();

        }

    }

    /**
     * 添加footer view 数据
     *
     * @param list
     */
    @Deprecated
    public void appendDataToFooterList(List<F> list) {

        appendListDataToFooterList(list, false);

    }

    /**
     * 添加footer view 数据
     *
     * @param list
     * @param isTop
     */
    public void appendListDataToFooterList(List<F> list, boolean isTop) {

        if (list == null) {

            return;

        }

        synchronized (mFooterList) {

            int position = mFooterList.size();

            mFooterList.ensureCapacity(position + list.size());

            if (isTop) {

                position = 0;

                mFooterList.addAll(position, list);

            } else {

                mFooterList.addAll(list);

            }

//            notifyFooterItemRangeChanged(position, list.size());

            notifyDataSetChanged();

        }

    }

    /**
     * 添加footer view 数据至底部
     *
     * @param list
     */
    public void appendListDataToFooterList(List<F> list) {

        appendListDataToFooterList(list, false);

    }

    /**
     * 添加footer view 数据至顶部
     *
     * @param list
     */
    public void appendListDataToFooterTopList(List<F> list) {

        appendListDataToFooterList(list, true);

    }

    /**
     * 刷新footer view  内容
     *
     * @param list
     */
    public void refreshFooterList(List<F> list) {

        if (list == null) {

            return;

        }

        mFooterList.clear();

        synchronized (mFooterList) {

            mFooterList.ensureCapacity(list.size());

            mFooterList.addAll(list);

            notifyDataSetChanged();

        }

    }

    /**
     * 添加footer
     *
     * @param f
     */
    @Deprecated
    public void appendItemDataToFooter(F f) {

        appendItemDataToFooterList(f, false);

    }

    /**
     * 添加footer
     *
     * @param f
     * @param isTop
     */
    public void appendItemDataToFooterList(F f, boolean isTop) {

        if (f == null) {

            return;

        }

        synchronized (mFooterList) {

            int position = mFooterList.size();

            if (isTop) {

                position = 0;

                mFooterList.add(position, f);

            } else {

                mFooterList.add(f);

            }

            notifyDataSetChanged();

        }

    }

    /**
     * 添加footer数据至列表底部
     *
     * @param f
     */
    public void appendItemDataToFooterList(F f) {

        appendItemDataToFooterList(f, false);

    }

    /**
     * 添加footer数据至列表顶部
     *
     * @param f
     */
    public void appendItemDataToFooterTopList(F f) {

        appendItemDataToFooterList(f, true);

    }

    public H getHeaderData(int position) {

        int headSize = getHeaderItemCount();

        if (position < headSize) {

            return mHeaderList.get(position);

        } else {

            return null;

        }

    }

    public C getContentData(int position) {

        int headSize = getHeaderItemCount();

        if (headSize > 0) {

            if (position > headSize + getContentItemCount()) {

                return null;

            } else {

                return mContentList.get(position - headSize);

            }

        } else {

            return mContentList.get(position);

        }

    }

    public F getFooterData(int position) {

        return mFooterList.get(position);

    }

    public ArrayList<H> getHeadList() {

        return mHeaderList;

    }

    public ArrayList<C> getContentList() {

        return mContentList;

    }

    public ArrayList<F> getFootList() {

        return mFooterList;

    }

    public void clear() {

        mHeaderList.clear();

        mContentList.clear();

        mFooterList.clear();

        notifyDataSetChanged();

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (isEmpty) {

            EmptyHolder holder = new EmptyHolder(mContext);

            return holder;

        } else {

            // Delegate to proper methods based on the viewType ranges.
            if (viewType >= HEADER_VIEW_TYPE_OFFSET && viewType < HEADER_VIEW_TYPE_OFFSET + VIEW_TYPE_MAX_COUNT) {
                return onCreateHeaderItemViewHolder(parent, viewType - HEADER_VIEW_TYPE_OFFSET);
            } else if (viewType >= FOOTER_VIEW_TYPE_OFFSET && viewType < FOOTER_VIEW_TYPE_OFFSET + VIEW_TYPE_MAX_COUNT) {
                return onCreateFooterItemViewHolder(parent, viewType - FOOTER_VIEW_TYPE_OFFSET);
            } else if (viewType >= CONTENT_VIEW_TYPE_OFFSET && viewType < CONTENT_VIEW_TYPE_OFFSET + VIEW_TYPE_MAX_COUNT) {
                return onCreateContentItemViewHolder(parent, viewType - CONTENT_VIEW_TYPE_OFFSET);
            } else {
                // This shouldn't happen as we check that the viewType provided by the client is valid.
                throw new IllegalStateException();
            }

        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {

        if (isEmpty) {

            if (viewHolder instanceof EmptyHolder) {

                ((EmptyHolder) viewHolder).bindData(emptyDes, emptyImg);

            }

        } else {

            // Delegate to proper methods based on the viewType ranges.
            if (headerItemCount > 0 && position < headerItemCount) {
                onBindHeaderItemViewHolder(viewHolder, position);
            } else if (contentItemCount > 0 && position - headerItemCount < contentItemCount) {
                onBindContentItemViewHolder(viewHolder, position - headerItemCount);
            } else {
                onBindFooterItemViewHolder(viewHolder, position - headerItemCount - contentItemCount);
            }

        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final int getItemCount() {

        if (isEmpty) {

            return 1;

        } else {

            // Cache the counts and return the sum of them.
            getAllTypeCount();

            return headerItemCount + contentItemCount + footerItemCount;

        }

    }

    private final void getAllTypeCount() {

        headerItemCount = getHeaderItemCount();

        contentItemCount = getContentItemCount();

        footerItemCount = getFooterItemCount();

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final int getItemViewType(int position) {

        if (isEmpty) {

            return 0;

        } else {
            // Delegate to proper methods based on the position, but validate first.
            if (headerItemCount > 0 && position < headerItemCount) {
                return validateViewType(getHeaderItemViewType(position)) + HEADER_VIEW_TYPE_OFFSET;
            } else if (contentItemCount > 0 && position - headerItemCount < contentItemCount) {
                return validateViewType(getContentItemViewType(position - headerItemCount)) + CONTENT_VIEW_TYPE_OFFSET;
            } else {
                return validateViewType(getFooterItemViewType(position - headerItemCount - contentItemCount)) + FOOTER_VIEW_TYPE_OFFSET;
            }

        }

    }

    /**
     * 显示空页面
     *
     * @param emptyDes
     * @param emptyImg
     */
    public void showEmptyView(String emptyDes, int emptyImg) {

        this.isEmpty = true;

        this.emptyDes = emptyDes;

        this.emptyImg = emptyImg;

        notifyDataSetChanged();

    }

    /**
     * 隐藏空页面
     */
    public void dismissEmptyView() {

        this.isEmpty = false;

        notifyDataSetChanged();

    }

    /**
     * Validates that the view type is within the valid range.
     *
     * @param viewType the view type.
     * @return the given view type.
     */
    private int validateViewType(int viewType) {
        if (viewType < 0 || viewType >= VIEW_TYPE_MAX_COUNT) {
            throw new IllegalStateException("viewType must be between 0 and " + VIEW_TYPE_MAX_COUNT);
        }
        return viewType;
    }

    /**
     * Notifies that a header item is inserted.
     *
     * @param position the position of the header item.
     */
    public final void notifyHeaderItemInserted(int position) {

        if (!isEmpty) {

            getAllTypeCount();

            if (position < 0 || position >= headerItemCount) {
                throw new IndexOutOfBoundsException("The given position " + position + " is not within the position bounds for header items [0 - " + (headerItemCount - 1) + "].");
            }

            notifyItemInserted(position);

        }

    }

    /**
     * Notifies that multiple header items are inserted.
     *
     * @param positionStart the position.
     * @param itemCount     the item count.
     */
    public final void notifyHeaderItemRangeInserted(int positionStart, int itemCount) {

        if (!isEmpty) {

            getAllTypeCount();

            if (positionStart < 0 || itemCount < 0 || positionStart + itemCount > headerItemCount) {
                throw new IndexOutOfBoundsException("The given range [" + positionStart + " - " + (positionStart + itemCount - 1) + "] is not within the position bounds for header items [0 - " + (headerItemCount - 1) + "].");
            }

            notifyItemRangeInserted(positionStart, itemCount);

        }
    }

    /**
     * Notifies that a header item is changed.
     *
     * @param position the position.
     */
    public final void notifyHeaderItemChanged(int position) {

        if (!isEmpty) {

            getAllTypeCount();

            if (position < 0 || position >= headerItemCount) {
                throw new IndexOutOfBoundsException("The given position " + position + " is not within the position bounds for header items [0 - " + (headerItemCount - 1) + "].");
            }
            notifyItemChanged(position);

        }
    }

    /**
     * Notifies that multiple header items are changed.
     *
     * @param positionStart the position.
     * @param itemCount     the item count.
     */
    public final void notifyHeaderItemRangeChanged(int positionStart, int itemCount) {

        if (!isEmpty) {

            getAllTypeCount();

            if (positionStart < 0 || itemCount < 0 || positionStart + itemCount > headerItemCount) {
                throw new IndexOutOfBoundsException("The given range [" + positionStart + " - " + (positionStart + itemCount - 1) + "] is not within the position bounds for header items [0 - " + (headerItemCount - 1) + "].");
            }

            notifyItemRangeChanged(positionStart, itemCount);

        }

    }


    /**
     * Notifies that an existing header item is moved to another position.
     *
     * @param fromPosition the original position.
     * @param toPosition   the new position.
     */
    public void notifyHeaderItemMoved(int fromPosition, int toPosition) {

        if (!isEmpty) {

            getAllTypeCount();

            if (fromPosition < 0 || toPosition < 0 || fromPosition >= headerItemCount || toPosition >= headerItemCount) {
                throw new IndexOutOfBoundsException("The given fromPosition " + fromPosition + " or toPosition " + toPosition + " is not within the position bounds for header items [0 - " + (headerItemCount - 1) + "].");
            }

            notifyItemMoved(fromPosition, toPosition);

        }

    }

    /**
     * Notifies that a header item is removed.
     *
     * @param position the position.
     */
    public void notifyHeaderItemRemoved(int position) {

        if (!isEmpty) {

            getAllTypeCount();

            if (position < 0 || position >= headerItemCount) {
                throw new IndexOutOfBoundsException("The given position " + position + " is not within the position bounds for header items [0 - " + (headerItemCount - 1) + "].");
            }

            notifyItemRemoved(position);

        }
    }

    /**
     * Notifies that multiple header items are removed.
     *
     * @param positionStart the position.
     * @param itemCount     the item count.
     */
    public void notifyHeaderItemRangeRemoved(int positionStart, int itemCount) {

        if (!isEmpty) {

            getAllTypeCount();

            if (positionStart < 0 || itemCount < 0 || positionStart + itemCount > headerItemCount) {
                throw new IndexOutOfBoundsException("The given range [" + positionStart + " - " + (positionStart + itemCount - 1) + "] is not within the position bounds for header items [0 - " + (headerItemCount - 1) + "].");
            }
            notifyItemRangeRemoved(positionStart, itemCount);

        }

    }

    /**
     * Notifies that a content item is inserted.
     *
     * @param position the position of the content item.
     */
    public final void notifyContentItemInserted(int position) {

        if (!isEmpty) {

            getAllTypeCount();

            if (position < 0 || position > contentItemCount) {
                throw new IndexOutOfBoundsException("The given position " + position + " is not within the position bounds for content items [0 - " + (contentItemCount - 1) + "].");
            }
            notifyItemInserted(position + headerItemCount);

        }
    }

    /**
     * Notifies that multiple content items are inserted.
     *
     * @param positionStart the position.
     * @param itemCount     the item count.
     */
    public final void notifyContentItemRangeInserted(int positionStart, int itemCount) {

        if (!isEmpty) {

            getAllTypeCount();

            if (positionStart < 0 || itemCount < 0 || positionStart + itemCount > contentItemCount) {
                throw new IndexOutOfBoundsException("The given range [" + positionStart + " - " + (positionStart + itemCount - 1) + "] is not within the position bounds for content items [0 - " + (contentItemCount - 1) + "].");
            }
            notifyItemRangeInserted(positionStart + headerItemCount, itemCount);

        }
    }

    /**
     * Notifies that a content item is changed.
     *
     * @param position the position.
     */
    public final void notifyContentItemChanged(int position) {

        if (!isEmpty) {

            getAllTypeCount();

            if (position < 0 || position > contentItemCount) {
                throw new IndexOutOfBoundsException("The given position " + position + " is not within the position bounds for content items [0 - " + (contentItemCount - 1) + "].");
            }
            notifyItemChanged(position + headerItemCount);

        }

    }

    /**
     * Notifies that multiple content items are changed.
     *
     * @param positionStart the position.
     * @param itemCount     the item count.
     */
    public final void notifyContentItemRangeChanged(int positionStart, int itemCount) {

        if (!isEmpty) {

            getAllTypeCount();

            if (positionStart < 0 || itemCount < 0 || positionStart + itemCount > contentItemCount) {
                throw new IndexOutOfBoundsException("The given range [" + positionStart + " - " + (positionStart + itemCount - 1) + "] is not within the position bounds for content items [0 - " + (contentItemCount - 1) + "].");
            }
            notifyItemRangeChanged(positionStart + headerItemCount, itemCount);

        }
    }

    /**
     * Notifies that an existing content item is moved to another position.
     *
     * @param fromPosition the original position.
     * @param toPosition   the new position.
     */
    public final void notifyContentItemMoved(int fromPosition, int toPosition) {

        if (!isEmpty) {

            getAllTypeCount();

            if (fromPosition < 0 || toPosition < 0 || fromPosition >= contentItemCount || toPosition >= contentItemCount) {
                throw new IndexOutOfBoundsException("The given fromPosition " + fromPosition + " or toPosition " + toPosition + " is not within the position bounds for content items [0 - " + (contentItemCount - 1) + "].");
            }
            notifyItemMoved(fromPosition + headerItemCount, toPosition + headerItemCount);

        }
    }

    /**
     * Notifies that a content item is removed.
     *
     * @param position the position.
     */
    public final void notifyContentItemRemoved(int position) {

        if (!isEmpty) {

            getAllTypeCount();

            if (position < 0 || position >= contentItemCount) {
                throw new IndexOutOfBoundsException("The given position " + position + " is not within the position bounds for content items [0 - " + (contentItemCount - 1) + "].");
            }
            notifyItemRemoved(position + headerItemCount);

        }

    }

    /**
     * Notifies that multiple content items are removed.
     *
     * @param positionStart the position.
     * @param itemCount     the item count.
     */
    public final void notifyContentItemRangeRemoved(int positionStart, int itemCount) {

        if (!isEmpty) {

            getAllTypeCount();

            if (positionStart < 0 || itemCount < 0 || positionStart + itemCount > contentItemCount) {
                throw new IndexOutOfBoundsException("The given range [" + positionStart + " - " + (positionStart + itemCount - 1) + "] is not within the position bounds for content items [0 - " + (contentItemCount - 1) + "].");
            }
            notifyItemRangeRemoved(positionStart + headerItemCount, itemCount);

        }
    }

    /**
     * Notifies that a footer item is inserted.
     *
     * @param position the position of the content item.
     */
    public final void notifyFooterItemInserted(int position) {

        if (!isEmpty) {

            getAllTypeCount();

            if (position < 0 || position > footerItemCount) {
                throw new IndexOutOfBoundsException("The given position " + position + " is not within the position bounds for footer items [0 - " + (footerItemCount - 1) + "].");
            }
            notifyItemInserted(position + headerItemCount + contentItemCount);

        }
    }

    /**
     * Notifies that multiple footer items are inserted.
     *
     * @param positionStart the position.
     * @param itemCount     the item count.
     */
    public final void notifyFooterItemRangeInserted(int positionStart, int itemCount) {

        if (!isEmpty) {

            getAllTypeCount();

            if (positionStart < 0 || itemCount < 0 || positionStart + itemCount > footerItemCount) {
                throw new IndexOutOfBoundsException("The given range [" + positionStart + " - " + (positionStart + itemCount - 1) + "] is not within the position bounds for footer items [0 - " + (footerItemCount - 1) + "].");
            }

            notifyItemRangeInserted(positionStart + headerItemCount + contentItemCount, itemCount);

        }
    }

    /**
     * Notifies that a footer item is changed.
     *
     * @param position the position.
     */
    public final void notifyFooterItemChanged(int position) {

        if (!isEmpty) {

            getAllTypeCount();

            if (position < 0 || position > footerItemCount) {
                throw new IndexOutOfBoundsException("The given position " + position + " is not within the position bounds for footer items [0 - " + (footerItemCount - 1) + "].");
            }

            notifyItemChanged(position + headerItemCount + contentItemCount);

        }
    }

    /**
     * Notifies that multiple footer items are changed.
     *
     * @param positionStart the position.
     * @param itemCount     the item count.
     */
    public final void notifyFooterItemRangeChanged(int positionStart, int itemCount) {

        if (!isEmpty) {

            getAllTypeCount();

            if (positionStart < 0 || itemCount < 0 || positionStart + itemCount > footerItemCount) {
                throw new IndexOutOfBoundsException("The given range [" + positionStart + " - " + (positionStart + itemCount - 1) + "] is not within the position bounds for footer items [0 - " + (footerItemCount - 1) + "].");
            }

            notifyItemRangeChanged(positionStart + headerItemCount + contentItemCount, itemCount);

        }
    }

    /**
     * Notifies that an existing footer item is moved to another position.
     *
     * @param fromPosition the original position.
     * @param toPosition   the new position.
     */
    public final void notifyFooterItemMoved(int fromPosition, int toPosition) {

        if (!isEmpty) {

            getAllTypeCount();

            if (fromPosition < 0 || toPosition < 0 || fromPosition >= footerItemCount || toPosition >= footerItemCount) {

                throw new IndexOutOfBoundsException("The given fromPosition " + fromPosition + " or toPosition " + toPosition + " is not within the position bounds for footer items [0 - " + (footerItemCount - 1) + "].");

            }

            notifyItemMoved(fromPosition + headerItemCount + contentItemCount, toPosition + headerItemCount + contentItemCount);

        }

    }

    /**
     * Notifies that a footer item is removed.
     *
     * @param position the position.
     */
    public final void notifyFooterItemRemoved(int position) {

        if (!isEmpty) {

            getAllTypeCount();

            if (position < 0 || position >= footerItemCount) {
                throw new IndexOutOfBoundsException("The given position " + position + " is not within the position bounds for footer items [0 - " + (footerItemCount - 1) + "].");
            }

            notifyItemRemoved(position + headerItemCount + contentItemCount);

        }
    }

    /**
     * Notifies that multiple footer items are removed.
     *
     * @param positionStart the position.
     * @param itemCount     the item count.
     */
    public final void notifyFooterItemRangeRemoved(int positionStart, int itemCount) {

        if (!isEmpty) {

            getAllTypeCount();

            if (positionStart < 0 || itemCount < 0 || positionStart + itemCount > footerItemCount) {
                throw new IndexOutOfBoundsException("The given range [" + positionStart + " - " + (positionStart + itemCount - 1) + "] is not within the position bounds for footer items [0 - " + (footerItemCount - 1) + "].");
            }

            notifyItemRangeRemoved(positionStart + headerItemCount + contentItemCount, itemCount);

        }

    }

    /**
     * Gets the header item view type. By default, this method returns 0.
     *
     * @param position the position.
     * @return the header item view type (within the range [0 - VIEW_TYPE_MAX_COUNT-1]).
     */
    protected int getHeaderItemViewType(int position) {
        return 0;
    }

    /**
     * Gets the footer item view type. By default, this method returns 0.
     *
     * @param position the position.
     * @return the footer item view type (within the range [0 - VIEW_TYPE_MAX_COUNT-1]).
     */
    protected int getFooterItemViewType(int position) {
        return 0;
    }

    /**
     * Gets the content item view type. By default, this method returns 0.
     *
     * @param position the position.
     * @return the content item view type (within the range [0 - VIEW_TYPE_MAX_COUNT-1]).
     */
    protected int getContentItemViewType(int position) {
        return 0;
    }

    /**
     * Gets the header item count. This method can be called several times, so it should not
     * calculate the count every time.
     *
     * @return the header item count.
     */
    protected int getHeaderItemCount() {

        return mHeaderList.size();

    }


    /**
     * Gets the footer item count. This method can be called several times, so it should not
     * calculate the count every time.
     *
     * @return the footer item count.
     */
    protected int getFooterItemCount() {

        return mFooterList.size();

    }

    /**
     * Gets the content item count. This method can be called several times, so it should not
     * calculate the count every time.
     *
     * @return the content item count.
     */
    protected int getContentItemCount() {

        return mContentList.size();

    }

    /**
     * This method works exactly the same as {@link #onCreateViewHolder(ViewGroup,
     * int)}, but for header items.
     *
     * @param parent         the parent view.
     * @param headerViewType the view type for the header.
     * @return the view holder.
     */
    protected abstract RecyclerView.ViewHolder onCreateHeaderItemViewHolder(ViewGroup parent, int headerViewType);

    /**
     * This method works exactly the same as {@link #onCreateViewHolder(ViewGroup,
     * int)}, but for content items.
     *
     * @param parent          the parent view.
     * @param contentViewType the view type for the content.
     * @return the view holder.
     */
    protected abstract RecyclerView.ViewHolder onCreateContentItemViewHolder(ViewGroup parent, int contentViewType);

    /**
     * This method works exactly the same as {@link #onCreateViewHolder(ViewGroup,
     * int)}, but for footer items.
     *
     * @param parent         the parent view.
     * @param footerViewType the view type for the footer.
     * @return the view holder.
     */
    protected abstract RecyclerView.ViewHolder onCreateFooterItemViewHolder(ViewGroup parent, int footerViewType);

    /**
     * This method works exactly the same as {@link #onBindViewHolder(android.support.v7.widget.RecyclerView.ViewHolder,
     * int)}, but for header items.
     *
     * @param headerViewHolder the view holder for the header item.
     * @param position         the position.
     */
    protected abstract void onBindHeaderItemViewHolder(RecyclerView.ViewHolder headerViewHolder, int position);

    /**
     * This method works exactly the same as {@link #onBindViewHolder(android.support.v7.widget.RecyclerView.ViewHolder,
     * int)}, but for content items.
     *
     * @param contentViewHolder the view holder for the content item.
     * @param position          the position.
     */
    protected abstract void onBindContentItemViewHolder(RecyclerView.ViewHolder contentViewHolder, int position);

    /**
     * This method works exactly the same as {@link #onBindViewHolder(android.support.v7.widget.RecyclerView.ViewHolder,
     * int)}, but for footer items.
     *
     * @param footerViewHolder the view holder for the footer item.
     * @param position         the position.
     */
    protected abstract void onBindFooterItemViewHolder(RecyclerView.ViewHolder footerViewHolder, int position);

    public interface OnItemClickListener {

        void onItemClick(View view, int position, int state);

    }

}